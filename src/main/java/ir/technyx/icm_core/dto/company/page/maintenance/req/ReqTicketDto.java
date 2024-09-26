package ir.technyx.icm_core.dto.company.page.maintenance.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqTicketDto {

    @NotBlank(message = "reqTicketDto.companyCode.notBlank")
    @Size(min = 5, max = 15, message = "reqTicketDto.companyCode.size")
    private String companyCode;

    @NotNull(message = "reqTicketDto.ticketTypeId.notNull")
    private Long ticketTypeId;

    @NotBlank(message = "reqTicketDto.title.notBlank")
    private String title;

    @NotBlank(message = "reqTicketDto.description.notBlank")
    private String description;

}
