package ir.technyx.icm_core.dto.user.accessManagement.request;

import ir.technyx.icm_core.dto.company.menu.request.ReqMenuAccessLevelItemDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqUserAccessDto {

    @NotNull(message = "reqUserAccessDto.userId.notNull")
    private Long userId;

    @NotNull(message = "reqDefaultMenuItemDto.roleId.notNull")
    private Long roleId;

    private List<ReqMenuAccessLevelItemDto> menuAccessLevelItemDtoList;


}
