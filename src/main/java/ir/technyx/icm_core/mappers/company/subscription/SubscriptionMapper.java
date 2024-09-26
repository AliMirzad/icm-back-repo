package ir.technyx.icm_core.mappers.company.subscription;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlan;
import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlanToCompany;
import ir.technyx.icm_core.dto.company.company.request.ReqSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.mappers.company.CompanyMapper;

import java.util.List;

public interface SubscriptionMapper {

    static SubscriptionPlan toSubscriptionPlan(Long id) {
        return new SubscriptionPlan(id);
    }

    static SubscriptionPlanToCompany toSubscriptionPlanToCompany(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto) {
        return new SubscriptionPlanToCompany(
                CompanyMapper.toCompany(reqSubscriptionPlanCompanyDto.getCompanyId()),
                SubscriptionMapper.toSubscriptionPlan(reqSubscriptionPlanCompanyDto.getSubscriptionPlanId()));
    }

    static ResSubscriptionPlanCompanyDto toResSubscriptionPlanCompanyDto(SubscriptionPlanToCompany subscriptionPlanToCompany) {
        Company owner = subscriptionPlanToCompany.getOwner();
        SubscriptionPlan subscriptionPlan = subscriptionPlanToCompany.getSubscriptionPlan();
        return new ResSubscriptionPlanCompanyDto(
                owner.getCode(),
                owner.getName(),
                subscriptionPlan.getCode(),
                subscriptionPlan.getTitle(),
                subscriptionPlan.getStartDate(),
                subscriptionPlan.getEndDate(),
                subscriptionPlan.getPrice(),
                subscriptionPlan.getDescription()
        );
    }

    static SubscriptionPlan toSubscriptionPlan(ReqSubscriptionPlanDto reqSubscriptionPlanDto) {
        return new SubscriptionPlan(
                reqSubscriptionPlanDto.getTitle(),
                reqSubscriptionPlanDto.getStartDate(),
                reqSubscriptionPlanDto.getEndDate(),
                reqSubscriptionPlanDto.getPrice(),
                reqSubscriptionPlanDto.getDescription(),
                SubscriptionPlanItemMapper.toSubscriptionPlanItem(reqSubscriptionPlanDto.getSubscriptionPlanItemsDto())
        );
    }

    static SubscriptionPlan totoSubscriptionPlan(ReqSubscriptionPlanUpdatableDto reqSubscriptionPlanUpdatableDto){
        return new SubscriptionPlan(
                reqSubscriptionPlanUpdatableDto.getId(),
                reqSubscriptionPlanUpdatableDto.getTitle(),
                reqSubscriptionPlanUpdatableDto.getStartDate(),
                reqSubscriptionPlanUpdatableDto.getEndDate(),
                reqSubscriptionPlanUpdatableDto.getPrice(),
                reqSubscriptionPlanUpdatableDto.getDescription(),
                SubscriptionPlanItemMapper
                        .toSubscriptionPlanItem(reqSubscriptionPlanUpdatableDto.getSubscriptionPlanItemsDto())
        );
    }

    static ResSubscriptionPlanDto toResSubscriptionPlanDto(SubscriptionPlan subscriptionPlan) {
        return new ResSubscriptionPlanDto(
                subscriptionPlan.getCode(),
                subscriptionPlan.getTitle(),
                subscriptionPlan.getStartDate(),
                subscriptionPlan.getEndDate()
        );
    }

    static ResSubscriptionPlanUpdatableDto toResSubscriptionPlanUpdatableDto(SubscriptionPlan subscriptionPlan) {
        return new ResSubscriptionPlanUpdatableDto(
                subscriptionPlan.getId(),
                subscriptionPlan.getTitle(),
                subscriptionPlan.getStartDate(),
                subscriptionPlan.getEndDate(),
                subscriptionPlan.getPrice(),
                subscriptionPlan.getDescription(),
                SubscriptionPlanItemMapper.toResSubscriptionPlanItemDto(subscriptionPlan.getSubscriptionPlanItems())
        );
    }

    static List<ResSubscriptionPlanUpdatableDto> toResSubscriptionPlanUpdatableDto(List<SubscriptionPlan> subscriptionPlans){
        return subscriptionPlans.stream().map(SubscriptionMapper::toResSubscriptionPlanUpdatableDto).toList();
    }


}
