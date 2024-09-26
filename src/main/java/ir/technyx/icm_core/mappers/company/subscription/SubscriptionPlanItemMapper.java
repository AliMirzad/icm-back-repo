package ir.technyx.icm_core.mappers.company.subscription;

import ir.technyx.icm_core.domain.company.subscription.SubscriptionPlanItem;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanItemDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanItemDto;
import ir.technyx.icm_core.mappers.common.ManagementTypeMapper;

import java.util.List;

public interface SubscriptionPlanItemMapper {

    static SubscriptionPlanItem toSubscriptionPlanItem(ReqSubscriptionPlanItemDto reqSubscriptionPlanItemDto){
        return new SubscriptionPlanItem(
                ManagementTypeMapper.toManagementType(reqSubscriptionPlanItemDto.getPageTypeId()),
                reqSubscriptionPlanItemDto.getTotalMediaSize(),
                reqSubscriptionPlanItemDto.getTotalItemSize()
        );
    }

    static List<SubscriptionPlanItem> toSubscriptionPlanItem(List<ReqSubscriptionPlanItemDto> reqSubscriptionPlanItemsDto){
        return reqSubscriptionPlanItemsDto.stream().map(SubscriptionPlanItemMapper::toSubscriptionPlanItem).toList();
    }

    static List<ResSubscriptionPlanItemDto> toResSubscriptionPlanItemDto(List<SubscriptionPlanItem> subscriptionPlanItems){
        return subscriptionPlanItems.stream().map(SubscriptionPlanItemMapper::toResSubscriptionPlanItemDto).toList();
    }

    static ResSubscriptionPlanItemDto toResSubscriptionPlanItemDto(SubscriptionPlanItem subscriptionPlanItem){
        return new ResSubscriptionPlanItemDto(
                subscriptionPlanItem.getId(),
                subscriptionPlanItem.getTotalMediaSize(),
                subscriptionPlanItem.getTotalItemSize()
        );
    }
}
