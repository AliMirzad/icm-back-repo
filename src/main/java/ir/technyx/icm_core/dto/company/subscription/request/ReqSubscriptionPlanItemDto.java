package ir.technyx.icm_core.dto.company.subscription.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqSubscriptionPlanItemDto {

    private Long pageTypeId;

    private Integer totalMediaSize;

    private Integer totalItemSize;

}
