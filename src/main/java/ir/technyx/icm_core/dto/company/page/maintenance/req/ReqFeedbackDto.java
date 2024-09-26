package ir.technyx.icm_core.dto.company.page.maintenance.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqFeedbackDto {

    @NotNull(message = "reqFeedbackDto.ticketId.notNull")
    private Long ticketId;

    @NotBlank(message = "reqFeedbackDto.answer.notBlank")
    private String answer;

}
