package ir.technyx.icm_core.service.company.page.faq;

import ir.technyx.icm_core.domain.company.page.faq.AskedQuestion;
import ir.technyx.icm_core.domain.company.page.faq.Faq;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDto;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDtoUpdatableDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionDetailDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionItem;
import ir.technyx.icm_core.mappers.company.page.AskedQuestionMapper;
import ir.technyx.icm_core.repository.company.page.AskedQuestionRepository;
import ir.technyx.icm_core.repository.company.page.FaqRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AskedQuestionServiceImpl implements AskedQuestionService {

    private final AskedQuestionRepository askedQuestionRepository;
    private final FaqRepository faqRepository;


    @Override
    @Transactional
    public ResAskedQuestionItem saveAskedQuestion(ReqAskedQuestionItemDto reqAskedQuestionItemDto) {
        AskedQuestion askedQuestion = AskedQuestionMapper.toAskedQuestion(reqAskedQuestionItemDto);
        Faq faq = faqRepository.findByCompanyCode(reqAskedQuestionItemDto.getCompanyCode())
                .orElseThrow(NullPointerException::new);
        askedQuestion.setFaq(faq);
        askedQuestionRepository.save(askedQuestion);
        return AskedQuestionMapper.toResAskedQuestionDto(askedQuestion);
    }

    @Override
    @Transactional
    public ResAskedQuestionItem updateAskedQuestion(ReqAskedQuestionItemDtoUpdatableDto reqAskedQuestionItemUpdatableDto) {
        AskedQuestion askedQuestion = AskedQuestionMapper.toAskedQuestion(reqAskedQuestionItemUpdatableDto);
        askedQuestionRepository.save(askedQuestion);
        return AskedQuestionMapper.toResAskedQuestionDto(askedQuestion);
    }

    @Override
    @Transactional
    public ResAskedQuestionDetailDto getAskedQuestion(Long askedQuestionId) {
        AskedQuestion askedQuestion = askedQuestionRepository.findById(askedQuestionId)
                .orElseThrow(NullPointerException::new);
        return AskedQuestionMapper.toResAskedQuestion(askedQuestion);
    }

    @Override
    @Transactional
    public List<ResAskedQuestionDetailDto> getAllAskedQuestionsByCompany(String companyCode) {
        Faq faq = faqRepository.findByCompanyCode(companyCode).orElseThrow(NullPointerException::new);
        return AskedQuestionMapper.toResAskedQuestion(faq.getAskedQuestions());
    }

    @Override
    @Transactional
    public void deleteAskedQuestion(Long askedQuestionId) {
        askedQuestionRepository.deleteById(askedQuestionId);
    }

    @Override
    @Transactional
    public void deleteAllAskedQuestionByCompany(String companyCode) {
        Faq faq = faqRepository.findByCompanyCode(companyCode).orElseThrow(NullPointerException::new);
        askedQuestionRepository.deleteAllByFaqId(faq.getId());
    }

}
