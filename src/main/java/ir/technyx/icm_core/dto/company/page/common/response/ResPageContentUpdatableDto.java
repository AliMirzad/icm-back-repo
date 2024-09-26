package ir.technyx.icm_core.dto.company.page.common.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResPageContentUpdatableDto extends ResBaseDto {

    private Long id;

    private String pageTitle;

    private String pageCode;

    private String contentTypeTitle;

    private String iconPath;

    private String title;

    private String metaKeyword;

    private String metaDescription;

    private boolean active;

    private String slug;

    private String description;

    private Integer priority;

    private String coverUrl;

    private List<ResMediaDto> media;

}
