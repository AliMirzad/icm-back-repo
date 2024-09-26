package ir.technyx.icm_core.dto.company.company_user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCompanyUserDto {

    @NotNull(message = "reqCompanyUserDto.companyId.notNull")
    private Long companyId;

    @NotNull(message = "reqCompanyUserDto.userId.notNull")
    private Long userId;

}
