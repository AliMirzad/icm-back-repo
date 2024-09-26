package ir.technyx.icm_core.mappers.company;

public interface CompanyUserMapper {
/*
    record CompanyUserDetail(Company company, User user, Role role) {
    }

    Function<ReqCompanyUserDto, CompanyUserDetail> toCompanyUserDetail = (companyUserDto ->
            new CompanyUserDetail(
                    CompanyMapper.toCompany.apply(companyUserDto.companyDto()),
                  //  UserMapper.toUser.apply(companyUserDto.userDto()),
                    RoleMapper.toRole.apply(companyUserDto.roleDto())

            )
    );

    Function<CompanyUserDetail, ReqCompanyUserDto> toCompanyUser = (companyUserDetail ->
            new ReqCompanyUserDto(
                    CompanyMapper.toCompanyDto.apply(companyUserDetail.company),
                    UserMapper.toUserDto.apply(companyUserDetail.user),
                    RoleMapper.toRoleDto.apply(companyUserDetail.role)
            )
    );*/


}
