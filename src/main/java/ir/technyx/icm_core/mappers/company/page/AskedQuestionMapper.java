package ir.technyx.icm_core.mappers.company.page;

import ir.technyx.icm_core.domain.company.page.faq.AskedQuestion;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionDetailDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionItem;

import java.util.List;
import java.util.stream.Collectors;

public interface AskedQuestionMapper {

    static ResAskedQuestionDetailDto toResAskedQuestion(AskedQuestion askedQuestion) {
        return new ResAskedQuestionDetailDto(askedQuestion.getId(),
                askedQuestion.getQuestion(),
                askedQuestion.getAnswer()
        );
    }

    static List<ResAskedQuestionDetailDto> toResAskedQuestion(List<AskedQuestion> askedQuestionList) {
        return askedQuestionList.stream()
                .map(AskedQuestionMapper::toResAskedQuestion).collect(Collectors.toList());

    }


    static AskedQuestion toAskedQuestion(ReqAskedQuestionItemDto reqAskedQuestionItemDto) {
        return new AskedQuestion(reqAskedQuestionItemDto.getQuestion(), reqAskedQuestionItemDto.getAnswer());
    }

    static ResAskedQuestionItem toResAskedQuestionDto(AskedQuestion askedQuestion) {
        return new ResAskedQuestionItem(askedQuestion.getId());
    }
}
