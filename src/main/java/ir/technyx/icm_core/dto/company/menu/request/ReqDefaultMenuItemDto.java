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
public class ReqDefaultMenuItemDto {

    @NotNull(message = "reqDefaultMenuItemDto.companyId.notNull")
    private Long companyId;

    @NotNull(message = "reqDefaultMenuItemDto.menuId.notNull")
    private Long menuId;

    @NotNull(message = "reqDefaultMenuItemDto.roleId.notNull")
    private Long roleId;

    private boolean write;

    private boolean read;

    private boolean update;

    private boolean delete;

    public ReqDefaultMenuItemDto(Long companyId, Long menuId, Long roleId, boolean allAccess) {
        this(companyId, menuId, roleId, allAccess, allAccess, allAccess, allAccess);
    }

}
