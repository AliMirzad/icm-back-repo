package ir.technyx.icm_core.dto.user.accessManagement.request;

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
public class ReqUserRolesDto {

    @NotNull(message = "reqUserAccessDto.userId.notNull")
    private Long userId;

    private List<Long> roleIds;

}
