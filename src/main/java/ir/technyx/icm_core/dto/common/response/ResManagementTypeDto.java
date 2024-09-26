package ir.technyx.icm_core.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResManagementTypeDto extends ResBaseDto {

    private Long id;

    private String title;

}
