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
public class ResCompanyDto extends ResBaseDto {

    private Long id;

    private String code;

    private String name;

}
