package ir.technyx.icm_core.dto.company.menu.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResMenuDto extends ResBaseDto {

    private Long id;

    private String title;

    private String companyTitle;

}
