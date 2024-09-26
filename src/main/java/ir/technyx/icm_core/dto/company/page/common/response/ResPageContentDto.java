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
public class ResPageContentDto extends ResBaseDto {

    private String pageTitle;

    private String pageCode;

    private String contentTypeTitle;

    private String title;

    private boolean active;

    private Integer priority;


}
