package ir.technyx.icm_core.dto.company.page.maintenance.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqTicketUpdatableDto extends ReqTicketDto {

    @NotNull(message = "reqTicketUpdatableDto.id.notNull")
    private Long id;

}
