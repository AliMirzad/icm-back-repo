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
public class ResCompanyListDto extends ResBaseDto {

    private Long id;

    private String code;

    private String name;

    private String nationalCode;

    private String postalCode;

    private String phone;

    private String email;

    private String webSite;

}
