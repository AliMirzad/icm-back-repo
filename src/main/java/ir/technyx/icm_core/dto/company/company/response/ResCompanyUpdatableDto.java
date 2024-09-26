package ir.technyx.icm_core.dto.company.company.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCompanyUpdatableDto extends ResBaseDto {

    private Long id;

    private String code;

    private String name;

    private String nationalCode;

    private Long locationInfoId;

    private String exactLocation;

    private String postalCode;

    private String phone;

    private String email;

    private String webSite;

    public ResCompanyUpdatableDto(Long id,String code,String name,String nationalCode,String phone,String email,String webSite){
        this(id,code,name,nationalCode,null,"","",phone,email,webSite);
    }

}
