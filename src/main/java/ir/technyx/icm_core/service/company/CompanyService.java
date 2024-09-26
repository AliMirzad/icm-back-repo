package ir.technyx.icm_core.service.company;

import ir.technyx.icm_core.dto.company.company.request.ReqCompanyDto;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.request.ReqSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyListDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.response.ResSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.page.common.request.ReqCompanyPageDto;
import ir.technyx.icm_core.dto.company.page.common.response.ResPageStatusDto;

import java.util.List;


public interface CompanyService {

    ResCompanyDto save(ReqCompanyDto reqCompanyDto);

    ResCompanyUpdatableDto update(ReqCompanyUpdatableDto reqCompanyUpdatableDto);

    ResCompanyUpdatableDto getCompany(Long id);

    ResCompanyUpdatableDto getCompany(String code);

    List<ResCompanyListDto> getAllCompany();

    void delete(Long id);

    void delete(String code);

    ResSubscriptionPlanCompanyDto
    activeSubscriptionPlanForCompany(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto);

    void addDefaultPages(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto);

    void changePageStatus(ReqCompanyPageDto reqCompanyPageDto);

    List<ResPageStatusDto> availablePages(Long subscriptionPlanId);

}
