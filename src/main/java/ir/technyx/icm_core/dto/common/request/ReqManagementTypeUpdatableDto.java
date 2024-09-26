package ir.technyx.icm_core.dto.common.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqManagementTypeUpdatableDto extends ReqManagementTypeDto {

    @NotNull(message = "reqManagementTypeDto.id.notNull")
    private Long id;

}
