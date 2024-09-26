package ir.technyx.icm_core.service.company.page.general;

import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.Media;
import ir.technyx.icm_core.domain.company.page.content.Content;
import ir.technyx.icm_core.domain.company.page.content.Page;
import ir.technyx.icm_core.dto.company.page.common.request.*;
import ir.technyx.icm_core.dto.company.page.common.response.*;
import ir.technyx.icm_core.mappers.company.page.PageMapper;
import ir.technyx.icm_core.repository.common.ManagementTypeRepository;
import ir.technyx.icm_core.repository.common.MediaRepository;
import ir.technyx.icm_core.repository.company.CompanyRepository;
import ir.technyx.icm_core.repository.company.page.ContentRepository;
import ir.technyx.icm_core.repository.company.page.PageRepository;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GeneralPageServiceImpl implements GeneralPageService {

    private final PageRepository pageRepository;

    private final ContentRepository contentRepository;

    private final ManagementTypeRepository managementTypeRepository;

    private final CompanyRepository companyRepository;

    private final MediaRepository mediaRepository;


    @Override
    public ResPageDto addPage(ReqPageDto reqPageDto) {
        //TODO @Import @Nader add Validation for check SubscriptionPlan for adding Page or Content
        Page page = PageMapper.toPage(reqPageDto);
        page.setCompany(companyRepository.findByCode(reqPageDto.getCompanyCode()).orElseThrow(NullPointerException::new));
        page.setPageCode(createPageCode(page));
        page.setRegistrationInfo(UserUtility.getCurrentRegistrationInfo());
        pageRepository.save(page);
        return PageMapper.toResPageDto(page);
    }

    private String createPageCode(Page page) {
        ManagementType pageType = managementTypeRepository.findById(page.getPageType().getId()).orElseThrow(NullPointerException::new);
        return page.getCompany().getCode().concat("_").concat(pageType.getSubType());
    }

    @Override
    public ResPageUpdatableDto updatePage(ReqPageUpdatableDto reqPageUpdatableDto) {
        Page page = PageMapper.toPage(reqPageUpdatableDto);
        pageRepository.save(page);
        return PageMapper.toResPageUpdatableDto(page);
    }

    @Override
    public ResPageUpdatableDto getPageById(Long id) {
        return PageMapper.toResPageUpdatableDto(pageRepository.findById(id)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public ResPageUpdatableDto getPageByPageCode(String code) {
        return PageMapper.toResPageUpdatableDto(pageRepository.findPageByPageCode(code)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public List<ResPageUpdatableDto> getAllPageByCompanyCode(String code) {
        return PageMapper.toResPageUpdatableDto(pageRepository.findAllByCompanyCode(code)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public void deletePageById(Long id) {
        pageRepository.deleteById(id);
    }

    @Override
    public void deleteAllPageByCompanyCode(String code) {
        pageRepository.deleteAllByCompanyCode(code);
    }

    @Override
    public ResPageContentDto addPageContent(ReqPageContentDto reqPageContentDto) {
        //TODO @Import @Nader add Validation for check SubscriptionPlan for adding Page or Content
        Content content = PageMapper.toContent(reqPageContentDto);
        content.setRegistrationInfo(UserUtility.getCurrentRegistrationInfo());
        contentRepository.save(content);
        return PageMapper.toResPageContentDto(content);
    }

    @Override
    public ResPageContentUpdatableDto updatePageContent(ReqPageContentUpdatableDto reqPageContentUpdatableDto) {
        Content content = PageMapper.toContent(reqPageContentUpdatableDto);
        contentRepository.save(content);
        return PageMapper.toResPageContentUpdatableDto(content);
    }

    @Override
    public ResPageContentUpdatableDto getPageContentById(Long id) {
        return PageMapper.toResPageContentUpdatableDto(contentRepository.findById(id)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public List<ResPageContentUpdatableDto> getPageContentByPageId(Long pageId) {
        return PageMapper.toResPageContentUpdatableDto(contentRepository
                .findAllByPageId(pageId)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public List<ResPageContentUpdatableDto> getPageContentByPageCode(String pageCode) {
        return PageMapper.toResPageContentUpdatableDto(contentRepository
                .findAllByPage_pageCode(pageCode)
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public void deletePageContentById(Long id) {
        contentRepository.deleteById(id);
    }

    @Override
    public void deleteAllPageContentByPageId(Long pageId) {
        contentRepository.deleteAllByPageId(pageId);
    }

    @Override
    public void deleteAllPageContentByPageCode(String pageCode) {
        contentRepository.deleteAllByPage_pageCode(pageCode);
    }

    @Override
    public ResMediaDto addMedia(ReqMediaDto reqMediaDto) {
        Media media = PageMapper.toMedia(reqMediaDto);
        mediaRepository.save(media);
        return PageMapper.toResMediaDto(media);
    }

}
