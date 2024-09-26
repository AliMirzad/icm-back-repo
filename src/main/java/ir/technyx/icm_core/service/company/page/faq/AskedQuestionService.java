package ir.technyx.icm_core.service.company.page.faq;

import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDto;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDtoUpdatableDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionDetailDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionItem;

import java.util.List;

public interface AskedQuestionService {
    ResAskedQuestionItem saveAskedQuestion(ReqAskedQuestionItemDto reqAskedQuestionItemDto);

    ResAskedQuestionItem updateAskedQuestion(ReqAskedQuestionItemDtoUpdatableDto reqAskedQuestionItemUpdatableDto);

    ResAskedQuestionDetailDto getAskedQuestion(Long askedQuestionId);

    List<ResAskedQuestionDetailDto> getAllAskedQuestionsByCompany(String companyCode);

    void deleteAskedQuestion(Long askedQuestionId);

    void deleteAllAskedQuestionByCompany(String companyCode);

}
