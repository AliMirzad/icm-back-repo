package ir.technyx.icm_core.dto.company.company.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCompanyUpdatableDto extends ReqCompanyDto {

    @NotNull(message = "reqCompanyDto.id.notNull")
    private Long id;

}
