package ir.technyx.icm_core.dto.company.page.maintenance.res;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResTicketUpdatableDto extends ResBaseDto {

    private Long id;

    private String code;

    private String companyCode;

    private Long ticketTypeId;

    private String title;

    private String description;

    private Long stateTypeId;

}
