package ir.technyx.icm_core.dto.company.subscription.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResSubscriptionPlanItemDto {

    private Long pageTypeId;

    private Integer totalMediaSize;

    private Integer totalItemSize;

}
