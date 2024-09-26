package ir.technyx.icm_core.dto.company.page.common.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqMediaDto {

    private Long contentId;

    private String title;

    private Long fileTypeId;

    private String url;

    private boolean active;

}
