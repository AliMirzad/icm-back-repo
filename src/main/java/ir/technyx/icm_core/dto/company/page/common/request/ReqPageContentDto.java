package ir.technyx.icm_core.dto.company.page.common.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqPageContentDto {

    private Long pageId;

    private Long contentTypeId;

    private String iconPath;

    private String title;

    private String metaKeyword;

    private String metaDescription;

    private boolean active;

    private String slug;

    private String description;

    private Integer priority;

    private String coverUrl;


}
