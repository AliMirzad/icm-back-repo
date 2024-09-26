package ir.technyx.icm_core.dto.company.subscription.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResSubscriptionPlanUpdatableDto extends ResBaseDto {

    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long price;

    private String description;

    private List<ResSubscriptionPlanItemDto> resSubscriptionPlanItemsDto;

}
