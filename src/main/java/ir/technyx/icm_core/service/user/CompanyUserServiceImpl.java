package ir.technyx.icm_core.service.user;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.dto.company.company_user.request.ReqCompanyUserDto;
import ir.technyx.icm_core.mappers.company.CompanyMapper;
import ir.technyx.icm_core.repository.company.CompanyRepository;
import ir.technyx.icm_core.repository.company.CompanyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyUserServiceImpl implements CompanyUserService {

    private final CompanyUserRepository companyUserRepository;

    private final CompanyRepository companyRepository;

    @Override
    public void save(ReqCompanyUserDto reqCompanyUserDto) {
        CompanyUser companyUser = CompanyMapper.toCompanyUser(reqCompanyUserDto);
        companyUserRepository.save(companyUser);
    }

    @Override
    public void save(CompanyUser companyUser) {
        companyUserRepository.save(companyUser);
    }

    @Override
    @Transactional
    public Company findCompanyByUserId(Long userId) {
        return companyUserRepository.findCompanyUserByUser_Id(userId).orElseThrow(NullPointerException::new).getCompany();
    }

    @Override
    @Transactional
    public Optional<List<User>> findAllIcmUser() {
        return companyUserRepository.findAllIcmUser();
    }

    @Override
    @Transactional
    public Optional<List<CompanyUser>> findAllCompanyUsers() {
        return companyUserRepository.findAllCompanyUsers();
    }

    @Override
    @Transactional
    public Optional<List<CompanyUser>> findAllCompanyUserByCompanyId(Long companyId) {
        return companyUserRepository.findAllByCompany_Id(companyId);
    }
}
