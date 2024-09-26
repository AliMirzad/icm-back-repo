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
public class ReqCompanyUserUpdatableDto extends ReqCompanyUserDto {

    @NotNull
    private Long id;

}