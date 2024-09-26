package ir.technyx.icm_core.dto.common.request;

import jakarta.validation.constraints.Min;
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
public class ReqManagementTypeDto {

    @NotBlank(message = "reqManagementTypeDto.typeCode.notBlank")
    @Size(min = 3, max = 10, message = "reqManagementTypeDto.typeCode.size")
    private String code;

    @NotBlank(message = "reqManagementTypeDto.subTypeCode.notBlank")
    @Size(min = 3, max = 10, message = "reqManagementTypeDto.subTypeCode.size")
    private String subType;

    @NotBlank(message = "reqManagementTypeDto.title.notBlank")
    @Size(min = 3, max = 256, message = "reqManagementTypeDto.title.size")
    private String title;

    @NotNull(message = "reqManagementTypeDto.priority.notNull")
    @Min(value = 0, message = "reqManagementTypeDto.priority.minimumValue")
    private Integer priority;

}
