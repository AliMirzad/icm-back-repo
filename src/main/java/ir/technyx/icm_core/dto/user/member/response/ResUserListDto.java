package ir.technyx.icm_core.dto.user.member.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserListDto extends ResBaseDto {

    private Long userId;

    private String username;

    private String companyName;

    private String companyCode;

}
