package ir.technyx.icm_core.dto.company.company.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCompanyDto {

    @NotBlank(message = "reqCompanyDto.name.notBlank")
    @Size(min = 5, message = "reqCompanyDto.name.size")
    private String name;

    @NotBlank(message = "reqCompanyDto.nationalCode.notBlank")
    @Size(min = 11, max = 11, message = "reqCompanyDto.nationalCode.size")
    private String nationalCode;

    @NotNull(message = "reqCompanyDto.locationInfoId.notNull")
    private Long locationInfoId;

    @NotBlank(message = "reqCompanyDto.exactLocation.notBlank")
    private String exactLocation;

    @NotBlank(message = "reqCompanyDto.postalCode.notBlank")
    private String postalCode;

    @NotBlank(message = "reqCompanyDto.phone.notBlank")
    private String phone;

    private String email;

    @NotBlank(message = "reqCompanyDto.hostUrl.notBlank")
    @Size(min = 5, message = "reqCompanyDto.hostUrl.size")
    private String hostUrl;

}
