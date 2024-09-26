package ir.technyx.icm_core.dto.company.page.common.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqCompanyPageDto {

    @NotNull(message = "reqCompanyPageDto.companyId.notNull")
    private Long companyId;

    private List<ReqPageStatusDto> reqPageStatusDto;

}
