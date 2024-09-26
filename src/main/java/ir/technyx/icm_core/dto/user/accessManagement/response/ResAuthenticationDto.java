package ir.technyx.icm_core.dto.user.accessManagement.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResAuthenticationDto extends ResBaseDto {

    private String accessToken;

    private String refreshToken;
}
