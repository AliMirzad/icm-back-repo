package ir.technyx.icm_core.service.user;

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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public interface UserService {

    ResAuthenticationDto authenticate(ReqAuthenticationDto request);

    ResUserDto registerUser(ReqIcmUserDto reqIcmUserDto);

    ResUserDto registerUser(ReqUserDto reqUserDto);

    ResIcmUserDetailsDto updateUser(ReqIcmUserUpdatableDto reqIcmUserUpdatableDto);

    ResUserDetail updateUser(ReqUserUpdatableDto reqUserUpdatableDto);

    void deleteUser(Long userId);

    ResIcmUserDetailsDto getIcmUser(Long id);

    List<ResIcmUserDetailsDto> getAllIcmUser();

    List<ResUserListDto> getAllUser();

    List<ResUserListDto> getAllUserByCompany(Long companyId);

    ResUserDetail getUser(Long id);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
