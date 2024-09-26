package ir.technyx.icm_core.dto.company.page.faq.request;

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
public class ReqAskedQuestionItemDto {

    @NotBlank(message = "reqAskedQuestionItemDto.companyCode.notBlank")
    @Size(min = 5, max = 15, message = "reqAskedQuestionItemDto.companyCode.size")
    private String companyCode;

    @NotBlank(message = "reqAskedQuestionItemDto.question.notBlank")
    @Size(min = 8, message = "reqAskedQuestionItemDto.question.size")
    private String question;

    @NotBlank(message = "reqAskedQuestionItemDto.answer.notBlank")
    @Size(min = 2, message = "reqAskedQuestionItemDto.answer.size")
    private String answer;

}
