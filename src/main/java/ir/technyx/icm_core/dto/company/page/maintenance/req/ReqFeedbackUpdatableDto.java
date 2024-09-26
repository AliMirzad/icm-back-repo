package ir.technyx.icm_core.dto.company.page.maintenance.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqFeedbackUpdatableDto extends ReqFeedbackDto {

    @NotNull(message = "reqFeedbackUpdatableDto.feedbackId.notNull")
    private Long id;

}
