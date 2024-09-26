package ir.technyx.icm_core.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.Token;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.dto.ResUserAuthenticationDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqAuthenticationDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResAuthenticationDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserUpdatableDto;
import ir.technyx.icm_core.dto.user.icm.response.ResIcmUserDetailsDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserUpdatableDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserDetail;
import ir.technyx.icm_core.dto.user.member.response.ResUserDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserListDto;
import ir.technyx.icm_core.mappers.user.UserMapper;
import ir.technyx.icm_core.repository.user.TokenRepository;
import ir.technyx.icm_core.repository.user.UserAccessRepository;
import ir.technyx.icm_core.repository.user.UserRepository;
import ir.technyx.icm_core.service.common.JwtService;
import ir.technyx.icm_core.service.company.CompanyService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAccessRepository userAccessRepository;
    private final CompanyUserService companyUserService;
    private final CompanyService companyService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final MessageUtil messageUtil;

    @Override
    public ResAuthenticationDto authenticate(ReqAuthenticationDto request) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        ResUserAuthenticationDto resUserAuthenticationDto = UserMapper.toAuthenticationDto(user);
        String jwtToken = jwtService.generateAccessToken(resUserAuthenticationDto.getUsername());
        String jwtRefreshToken = jwtService.generateRefreshToken(resUserAuthenticationDto.getUsername());
        revokeAllAccountTokens(user.getId());

        saveToken(user, jwtToken);

        return ResAuthenticationDto.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken).build();
    }

    private void saveToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setJwtToken(jwtToken);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    private void revokeAllAccountTokens(Long userId) {
        List<Token> allByValidTokensByAccount =
                tokenRepository.findAllByValidTokensByUser(userId);

        allByValidTokensByAccount.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(allByValidTokensByAccount);
    }


    @Override
    @Transactional
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String refreshToken = authHeader.substring((7));
        String username = jwtService.extractUsername(refreshToken);

        if (StringUtils.hasText(username)) {

            User user = userRepository.findByUsername(username).orElseThrow(NullPointerException::new);


            if (jwtService.isTokenValid(refreshToken, user.getUsername())) {
                String accessToken = jwtService.generateAccessToken(user.getUsername());
                refreshToken = jwtService.generateRefreshToken(user.getUsername());
                ResAuthenticationDto resAuthenticationDto = ResAuthenticationDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                resAuthenticationDto.setResponseTime(LocalDateTime.now());
                resAuthenticationDto.setResponseMessage(messageUtil.getLocalizedMessage("security.createNewTokenByRefreshToken"));
                revokeAllAccountTokens(user.getId());
                saveToken(user, accessToken);


                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.ACCEPTED.value());
                OutputStream responseStream = response.getOutputStream();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.writeValue(responseStream, resAuthenticationDto);
                responseStream.flush();

            }
        }
    }

    @Override
    @Transactional
    public ResUserDto registerUser(ReqIcmUserDto reqIcmUserDto) {
        User user = UserMapper.toUser(reqIcmUserDto);
        setDefaultAuthenticationInfo(user);
        userRepository.save(user);
        Long companyId = companyService.getCompany(Company.ICM_COMPANY_CODE).getId();
        CompanyUser companyUser = new CompanyUser(companyId, user.getId(), UserUtility.getCurrentRegistrationInfo());
        companyUserService.save(companyUser);
        return UserMapper.toResUserDto(user);
    }

    @Override
    @Transactional
    public ResUserDto registerUser(ReqUserDto reqUserDto) {
        User user = UserMapper.toUser(reqUserDto);
        setDefaultAuthenticationInfo(user);
        userRepository.save(user);
        if (!UserUtility.haveIcmRole()) {
            Company company = companyUserService.findCompanyByUserId(UserUtility.getCurrentUserId());
            CompanyUser companyUser = new CompanyUser(company, user, UserUtility.getCurrentRegistrationInfo());
            companyUserService.save(companyUser);
        }
        return UserMapper.toResUserDto(user);
    }

    @Override
    @Transactional
    public ResIcmUserDetailsDto updateUser(ReqIcmUserUpdatableDto reqIcmUserUpdatableDto) {
        User user = UserMapper.toUser(reqIcmUserUpdatableDto);//TODO @nader username is updatable false ,
        setAuthenticationInfoFromOldUser(user);
        userRepository.save(user);
        return UserMapper.toResIcmUserDetailDto(user);
    }

    @Override
    @Transactional
    public ResUserDetail updateUser(ReqUserUpdatableDto reqUserUpdatableDto) {
        User user = UserMapper.toUser(reqUserUpdatableDto);
        setAuthenticationInfoFromOldUser(user);
        setUserAdditionalInfoFromOldUser(user);
        userRepository.save(user);
        userRepository.flush();
        user = userRepository.findById(user.getId()).orElseThrow(NullPointerException::new);
        return UserMapper.toResUserDetail(user);
    }

    private void setDefaultAuthenticationInfo(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
    }

    private void setAuthenticationInfoFromOldUser(User user) {
        User oldUser = userRepository.findById(user.getId()).orElseThrow(NullPointerException::new);
        user.setAccountNonExpired(oldUser.isAccountNonExpired());
        user.setAccountNonLocked(oldUser.isAccountNonLocked());
        user.setEnabled(oldUser.isEnabled());
        user.setCredentialsNonExpired(oldUser.isCredentialsNonExpired());
        user.setPassword(oldUser.getPassword());
    }

    private void setUserAdditionalInfoFromOldUser(User user) {
        User oldUser = userRepository.findById(user.getId()).orElseThrow(NullPointerException::new);
        user.getAddress().setId(oldUser.getAddress().getId());
    }

    @Override
    @Transactional
    public ResIcmUserDetailsDto getIcmUser(Long id) {//todo add validator before get icm user
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        return UserMapper.toResIcmUserDetailDto(user);
    }

    @Override
    @Transactional
    public List<ResIcmUserDetailsDto> getAllIcmUser() {
        List<User> users = companyUserService.findAllIcmUser().orElse(new ArrayList<>());
        return UserMapper.toResIcmUserDetailsListDto(users);
    }

    @Override
    public List<ResUserListDto> getAllUser() {
        List<User> users = userRepository.findAllWithOutCompanyUser().orElse(new ArrayList<>());
        return UserMapper.toResUserListDto(users);
    }

    @Override
    @Transactional
    public List<ResUserListDto> getAllUserByCompany(Long companyId) {//todo refactor this code
        List<CompanyUser> companyUsers = companyUserService.findAllCompanyUserByCompanyId(companyId).orElseThrow(NullPointerException::new);
        return UserMapper.companyUsersToResUserListDto(companyUsers);
    }

    @Override
    @Transactional
    public ResUserDetail getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        return UserMapper.toResUserDetail(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userAccessRepository.deleteAllByUser_Id(userId);
        userRepository.deleteById(userId);
    }
}
