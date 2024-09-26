package ir.technyx.icm_core.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResManagementTypeForListDto extends ResBaseDto {

    private Long id;

    private String title;

    private String code;

    private Integer priority;

}
