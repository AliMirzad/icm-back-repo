package ir.technyx.icm_core.dto.user.accessManagement.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import ir.technyx.icm_core.dto.common.response.ResRoleDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserRolesDto extends ResBaseDto {

    private Long userId;

    private String username;

    private List<ResRoleDetailsDto> resRoleDetailsDtoList;

}
