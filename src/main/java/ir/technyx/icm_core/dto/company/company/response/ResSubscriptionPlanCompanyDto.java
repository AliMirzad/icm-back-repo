package ir.technyx.icm_core.dto.company.company.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResSubscriptionPlanCompanyDto extends ResBaseDto {

    private String companyCode;

    private String companyName;

    private String subscriptionPlanCode;

    private String subscriptionPlanTitle;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long price;

    private String description;

}
