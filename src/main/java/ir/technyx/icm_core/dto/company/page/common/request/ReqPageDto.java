package ir.technyx.icm_core.dto.company.page.common.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqPageDto {

    private Long CompanyId;

    private String companyCode;

    private Long pageTypeId;

    private String title;

    private String iconPath;

    private String metaKeyword;

    private String metaDescription;

    private boolean active;

    private String slug;

}
