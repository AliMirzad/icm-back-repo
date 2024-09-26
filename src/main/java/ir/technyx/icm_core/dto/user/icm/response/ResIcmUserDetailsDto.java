package ir.technyx.icm_core.dto.user.icm.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResIcmUserDetailsDto extends ResBaseDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

}
