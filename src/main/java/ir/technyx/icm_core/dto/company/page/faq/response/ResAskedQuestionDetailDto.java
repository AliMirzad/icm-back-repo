package ir.technyx.icm_core.dto.company.page.faq.response;

import ir.technyx.icm_core.dto.common.response.ResBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResAskedQuestionDetailDto extends ResBaseDto {

    private Long id;

    private String question;

    private String answer;

}
