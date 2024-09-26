package ir.technyx.icm_core.dto.common.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResRoleDetailsDto extends ResBaseDto {

    private Long id;

    private String name;

    private boolean active;

}
