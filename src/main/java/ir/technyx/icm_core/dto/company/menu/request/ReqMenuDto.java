package ir.technyx.icm_core.dto.company.menu.request;

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
@Deprecated
public class ReqMenuDto {

    @NotNull(message = "reqDefaultMenuItemDto.companyId.notNull")
    private Long companyId;

    @NotNull(message = "reqDefaultMenuItemDto.menuTypeId.notNull")
    private Long menuTypeId;

    @NotBlank(message = "reqMenuDto.title.notBlank")
    @Size(min = 5, message = "reqMenuDto.title.size")
    private String title;

    @NotBlank(message = "reqMenuDto.url.notBlank")
    @Size(min = 5, message = "reqMenuDto.url.size")
    private String url;

    private String iconPath;

    private boolean active;

}
