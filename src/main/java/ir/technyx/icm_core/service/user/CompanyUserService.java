package ir.technyx.icm_core.service.user;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.dto.company.company_user.request.ReqCompanyUserDto;

import java.util.List;
import java.util.Optional;

public interface CompanyUserService {

    void save(ReqCompanyUserDto reqCompanyUserDto);

    void save(CompanyUser companyUser);

    Company findCompanyByUserId(Long userId);

    Optional<List<User>> findAllIcmUser();

    Optional<List<CompanyUser>> findAllCompanyUsers();

    Optional<List<CompanyUser>> findAllCompanyUserByCompanyId(Long companyId);

}
