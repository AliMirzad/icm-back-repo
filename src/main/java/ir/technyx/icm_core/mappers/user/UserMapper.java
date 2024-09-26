package ir.technyx.icm_core.mappers.user;


import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.domain.user.UserAccess;
import ir.technyx.icm_core.dto.ResUserAuthenticationDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserRolesDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserUpdatableDto;
import ir.technyx.icm_core.dto.user.icm.response.ResIcmUserDetailsDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserUpdatableDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserDetail;
import ir.technyx.icm_core.dto.user.member.response.ResUserDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserListDto;
import ir.technyx.icm_core.mappers.common.AddressMapper;
import ir.technyx.icm_core.mappers.common.ManagementTypeMapper;
import ir.technyx.icm_core.utils.UserUtility;

import java.util.List;
import java.util.stream.Collectors;

public interface UserMapper {

    static User toUser(ReqIcmUserDto reqIcmUserDto) {
        return new User(
                reqIcmUserDto.getUsername(),
                UserUtility.encodePassword(reqIcmUserDto.getPassword()),
                reqIcmUserDto.getFirstName(),
                reqIcmUserDto.getLastName(),
                reqIcmUserDto.getPhone(),
                reqIcmUserDto.getEmail(),
                reqIcmUserDto.isRememberMe()
        );
    }


    static User toUser(ReqIcmUserUpdatableDto reqIcmUserUpdatableDto) {
        return new User(
                reqIcmUserUpdatableDto.getId(),
                reqIcmUserUpdatableDto.getUsername(),
                reqIcmUserUpdatableDto.getFirstName(),
                reqIcmUserUpdatableDto.getLastName(),
                reqIcmUserUpdatableDto.getPhone(),
                reqIcmUserUpdatableDto.getEmail()
        );
    }

    static ResIcmUserDetailsDto toResIcmUserDetailDto(User user) {

        return new ResIcmUserDetailsDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail()
        );

    }

    static List<ResIcmUserDetailsDto> toResIcmUserDetailsListDto(List<User> users) {
        return users.stream().map(u ->
                new ResIcmUserDetailsDto(u.getId(), u.getUsername(), u.getFirstName(),
                        u.getLastName(), u.getPhone(), u.getEmail())
        ).collect(Collectors.toList());
    }

    static ResUserDto toResUserDto(User user) {
        return new ResUserDto(user.getId(), user.getUsername());
    }

    static List<ResUserListDto> toResUserListDto(List<User> users) {
        return users.stream().map(UserMapper::toResUserListDto).collect(Collectors.toList());
    }

    static ResUserListDto toResUserListDto(User user) {
        return new ResUserListDto(user.getId(), user.getUsername(), null, null);
    }

    static List<ResUserListDto> companyUsersToResUserListDto(List<CompanyUser> companyUsers) {
        return companyUsers.stream().map(UserMapper::companyUsersToResUserListDto).collect(Collectors.toList());
    }

    static ResUserListDto companyUsersToResUserListDto(CompanyUser companyUser) {
        User user = companyUser.getUser();
        Company company = companyUser.getCompany();
        return new ResUserListDto(user.getId(), user.getUsername(), company.getName(), company.getCode());

    }

    static ResUserDetail toResUserDetail(User user) {
        return new ResUserDetail(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getGender().getId(),
                user.getAddress().getLocationInfo().getId(),
                user.getAddress().getExactLocation(),
                user.getAddress().getPostalCode(),
                user.getPhone(),
                user.getEmail(),
                user.getNationalCode()
        );
    }


    static User toUser(ReqUserDto reqUserDto) {

        return new User(
                reqUserDto.getUsername(),
                UserUtility.encodePassword(reqUserDto.getPassword()),
                reqUserDto.getFirstName(),
                reqUserDto.getLastName(),
                reqUserDto.getBirthDate(),

                ManagementTypeMapper.toManagementType(reqUserDto.getGenderId()),
                AddressMapper.toAddress(reqUserDto.getLocationInfoId(),
                        reqUserDto.getExactLocation(),
                        reqUserDto.getPostalCode()
                ),

                reqUserDto.getPhone(),
                reqUserDto.getEmail(),
                reqUserDto.getNationalCode(),
                reqUserDto.isRememberMe()
        );
    }

    static User toUser(ReqUserUpdatableDto reqUserUpdatableDto) {
        return new User(
                reqUserUpdatableDto.getId(),
                reqUserUpdatableDto.getUsername(),
                reqUserUpdatableDto.getFirstName(),
                reqUserUpdatableDto.getLastName(),
                reqUserUpdatableDto.getBirthDate(),

                ManagementTypeMapper.toManagementType(reqUserUpdatableDto.getGenderId()),
                AddressMapper.toAddress(reqUserUpdatableDto.getLocationInfoId(),
                        reqUserUpdatableDto.getExactLocation(),
                        reqUserUpdatableDto.getPostalCode()
                ),

                reqUserUpdatableDto.getPhone(),
                reqUserUpdatableDto.getEmail(),
                reqUserUpdatableDto.getNationalCode()
        );
    }

    static List<UserAccess> toUserAccessList(ReqUserRolesDto reqUserRolesDto) {
        Long userId = reqUserRolesDto.getUserId();
        return reqUserRolesDto.getRoleIds().stream().map(r -> {
            UserAccess userAccess = new UserAccess(userId);
            userAccess.setRole(new Role(r));
            return userAccess;
        }).collect(Collectors.toList());
    }

    static ResUserAuthenticationDto toAuthenticationDto(User user) {
        ResUserAuthenticationDto resUserAuthenticationDto = new ResUserAuthenticationDto();
        resUserAuthenticationDto.setId(user.getId());
        resUserAuthenticationDto.setUsername(user.getUsername());
        resUserAuthenticationDto.setPassword(user.getPassword());
        resUserAuthenticationDto.setRememberMe(user.isRememberMe());
        resUserAuthenticationDto.setAccountNonExpired(user.isAccountNonExpired());
        resUserAuthenticationDto.setAccountNonLocked(user.isAccountNonLocked());
        resUserAuthenticationDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
        resUserAuthenticationDto.setEnabled(user.isEnabled());
        return resUserAuthenticationDto;
    }

}
