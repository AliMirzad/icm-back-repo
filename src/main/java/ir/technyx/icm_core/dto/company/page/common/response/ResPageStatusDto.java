package ir.technyx.icm_core.dto.company.page.common.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResPageStatusDto extends ResBaseDto {

    private String pageName;

    private boolean active;

}
