package ir.technyx.icm_core.dto.company.subscription.request;

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
public class ReqSubscriptionPlanDto {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long price;

    private String description;

    private List<ReqSubscriptionPlanItemDto> subscriptionPlanItemsDto;


}
