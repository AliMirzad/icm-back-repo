package ir.technyx.icm_core.dto.company.subscription.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResSubscriptionPlanDto extends ResBaseDto {

    private String code;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
