package ir.technyx.icm_core.service.company.subscription;

import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanUpdatableDto;

import java.util.List;

public interface SubscriptionPlanService {
    ResSubscriptionPlanDto saveSubscriptionPlan(ReqSubscriptionPlanDto reqSubscriptionPlanDto);

    ResSubscriptionPlanUpdatableDto updateSubscriptionPlan(ReqSubscriptionPlanUpdatableDto reqSubscriptionPlanUpdatableDto);

    ResSubscriptionPlanUpdatableDto getSubscriptionPlanById(Long id);

    ResSubscriptionPlanUpdatableDto getSubscriptionPlanByCode(String code);

    List<ResSubscriptionPlanUpdatableDto> getAllSubscriptionPlan();

    void delete(Long id);

    void delete(String code);

}
