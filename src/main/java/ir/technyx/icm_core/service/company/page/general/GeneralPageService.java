package ir.technyx.icm_core.service.company.page.general;

import ir.technyx.icm_core.dto.company.page.common.request.*;
import ir.technyx.icm_core.dto.company.page.common.response.*;

import java.util.List;

public interface GeneralPageService {
    ResPageDto addPage(ReqPageDto reqPageDto);

    ResPageUpdatableDto updatePage(ReqPageUpdatableDto reqPageUpdatableDto);

    ResPageUpdatableDto getPageById(Long id);

    ResPageUpdatableDto getPageByPageCode(String code);

    List<ResPageUpdatableDto> getAllPageByCompanyCode(String code);

    void deletePageById(Long id);

    void deleteAllPageByCompanyCode(String code);

    ResPageContentDto addPageContent(ReqPageContentDto reqPageContentDto);

    ResPageContentUpdatableDto updatePageContent(ReqPageContentUpdatableDto reqPageContentUpdatableDto);

    ResPageContentUpdatableDto getPageContentById(Long id);

    List<ResPageContentUpdatableDto> getPageContentByPageId(Long pageId);

    List<ResPageContentUpdatableDto> getPageContentByPageCode(String pageCode);

    void deletePageContentById(Long id);

    void deleteAllPageContentByPageId(Long pageId);

    void deleteAllPageContentByPageCode(String pageCode);

    ResMediaDto addMedia(ReqMediaDto reqMediaDto);


}
