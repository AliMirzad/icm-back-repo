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
public class ReqMenuAccessLevelItemDto {

    @NotNull(message = "reqDefaultMenuItemDto.menuId.notNull")
    private Long menuId;

    private boolean read;

    private boolean write;

    private boolean update;

    private boolean delete;

}
