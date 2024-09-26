package ir.technyx.icm_core.service.company;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlan;
import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlanToCompany;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyDto;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.request.ReqSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyListDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.response.ResSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.page.common.request.ReqCompanyPageDto;
import ir.technyx.icm_core.dto.company.page.common.response.ResPageStatusDto;
import ir.technyx.icm_core.mappers.company.CompanyMapper;
import ir.technyx.icm_core.mappers.company.page.PageMapper;
import ir.technyx.icm_core.mappers.company.subscription.SubscriptionMapper;
import ir.technyx.icm_core.repository.company.CompanyRepository;
import ir.technyx.icm_core.repository.company.subscription.SubscriptionPlanRepository;
import ir.technyx.icm_core.repository.company.subscription.SubscriptionPlanToCompanyRepository;
import ir.technyx.icm_core.service.company.page.PageInfo;
import ir.technyx.icm_core.service.company.page.PageManagement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final SubscriptionPlanToCompanyRepository subscriptionPlanToCompanyRepository;

    private final List<PageManagement<PageInfo>> pageManagementImplementations;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    @Transactional
    public ResCompanyDto save(ReqCompanyDto reqCompanyDto) {
        Company company = CompanyMapper.toCompany(reqCompanyDto);
        companyRepository.save(company);
        return CompanyMapper.toResCompanyDto(company);
    }

    @Override
    @Transactional
    public ResCompanyUpdatableDto update(ReqCompanyUpdatableDto reqCompanyUpdatableDto) {
        Company company = CompanyMapper.toCompany(reqCompanyUpdatableDto);
        return CompanyMapper.toResCompanyUpdatableDto(companyRepository.save(company));
    }

    @Override
    @Transactional
    public ResCompanyUpdatableDto getCompany(Long id) {
        return CompanyMapper
                .toResCompanyUpdatableDto(companyRepository
                        .findById(id).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public ResCompanyUpdatableDto getCompany(String code) {
        return CompanyMapper
                .toResCompanyUpdatableDto(companyRepository
                        .findByCode(code).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResCompanyListDto> getAllCompany() {
        return CompanyMapper.toResCompanyListDto(companyRepository.findAll());
    }

    //TODO @nader delete every company child and then delete company
    @Override
    @Transactional
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(String code) {
        companyRepository.deleteByCode(code);
    }

    @Override
    @Transactional
    public ResSubscriptionPlanCompanyDto activeSubscriptionPlanForCompany(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto) {
        SubscriptionPlanToCompany subscriptionPlanToCompany = SubscriptionMapper
                .toSubscriptionPlanToCompany(reqSubscriptionPlanCompanyDto);

        subscriptionPlanToCompanyRepository.save(subscriptionPlanToCompany);
        return SubscriptionMapper.toResSubscriptionPlanCompanyDto(subscriptionPlanToCompany);
    }

    @Override
    @Transactional
    public void addDefaultPages(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository
                .findById(reqSubscriptionPlanCompanyDto.getSubscriptionPlanId())
                .orElseThrow(NullPointerException::new);
        List<String> pageTypeCodes = subscriptionPlan.getSubscriptionPlanItems().stream()
                .map(spi -> spi.getPageType().getCode())
                .toList();

        pageTypeCodes.forEach(ptc -> {
            PageInfo pageInfo = new PageInfo(reqSubscriptionPlanCompanyDto.getCompanyId(), ptc);
            pageManagementImplementations.stream().filter(pa -> pa.support(pageInfo))
                    .findFirst().ifPresent(pa -> pa.addPage(pageInfo));
        });

    }

    @Override
    public void changePageStatus(ReqCompanyPageDto reqCompanyPageDto) {
        reqCompanyPageDto.getReqPageStatusDto().forEach(requestPage -> {
            PageInfo pageInfo = new PageInfo(reqCompanyPageDto.getCompanyId(), requestPage.getPageName());
            pageManagementImplementations.stream().filter(pa -> pa.support(pageInfo)).findFirst()
                    .ifPresent(pa -> {
                        if (requestPage.isActive()) {
                            pa.activePage(pageInfo);
                        } else {
                            pa.deactivatePage(pageInfo);
                        }
                    });
        });
    }

    @Transactional
    @Override
    public List<ResPageStatusDto> availablePages(Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository
                .findById(subscriptionPlanId).orElseThrow(NullPointerException::new);
        return PageMapper
                .toResPageStatusDto(subscriptionPlan.getSubscriptionPlanItems()
                        .stream().map(spi -> spi.getPageType().getCode())
                        .toList()
                );

    }

}
