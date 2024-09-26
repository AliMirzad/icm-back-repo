package ir.technyx.icm_core.dto.company.menu.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqMenuUpdatableDto extends ReqMenuDto {

    @NotNull(message = "reqMenuUpdatableDto.id.notNull")
    private Long id;

}
