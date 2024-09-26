package ir.technyx.icm_core.dto.company.page.common.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqPageStatusDto {

    @NotBlank(message = "reqPageStatusDto.pageName.notBlank")
    @Size(min = 3, message = "reqPageStatusDto.pageName.size")
    private String pageName;

    private boolean active;

}
