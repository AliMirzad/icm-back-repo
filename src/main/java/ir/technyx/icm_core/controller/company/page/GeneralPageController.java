package ir.technyx.icm_core.controller.company.page;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.page.common.request.*;
import ir.technyx.icm_core.dto.company.page.common.response.*;
import ir.technyx.icm_core.service.company.page.general.GeneralPageService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/page")
@AllArgsConstructor
public class GeneralPageController {

    private final GeneralPageService generalPageService;

    private final MessageUtil messageUtil;

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/addPage")
    public ResponseEntity<ResPageDto> addPage(@Valid @RequestBody ReqPageDto reqPageDto) {
        return ResponseEntityHelper.created(generalPageService.addPage(reqPageDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updatePage")
    public ResponseEntity<ResPageUpdatableDto> updatePage(@Valid @RequestBody ReqPageUpdatableDto reqPageUpdatableDto) {
        return ResponseEntityHelper.accepted(generalPageService.updatePage(reqPageUpdatableDto));
    }


    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getPageById/{id}")
    public ResponseEntity<ResPageUpdatableDto> getPageById(@PathVariable("id") Long id) {
        return ResponseEntityHelper.ok(generalPageService.getPageById(id));
    }

    @GetMapping("/v1/getPageByCode/{pageCode}")
    public ResponseEntity<ResPageUpdatableDto> getPageByCode(@PathVariable("pageCode") String pageCode) {
        return ResponseEntityHelper.ok(generalPageService.getPageByPageCode(pageCode));
    }

    @GetMapping("/v1/getAllPageByCompanyCode/{code}")
    public ResponseEntity<List<ResPageUpdatableDto>> getAllPageByCompanyCode(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(generalPageService.getAllPageByCompanyCode(code));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deletePageById/{id}")
    public ResponseEntity<ResDeletedDto> deletePageById(@PathVariable("id") Long id) {
        generalPageService.deletePageById(id);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(id,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllPageByCompanyCode/{code}")
    public ResponseEntity<ResDeletedDto> deleteAllPageByCompanyCode(@PathVariable("code") String code) {
        generalPageService.deleteAllPageByCompanyCode(code);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(code,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/addPageContent")
    public ResponseEntity<ResPageContentDto> addPageContent(@Valid @RequestBody ReqPageContentDto reqPageContentDto) {
        return ResponseEntityHelper.created(generalPageService.addPageContent(reqPageContentDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updatePageContent")
    public ResponseEntity<ResPageContentUpdatableDto> updatePageContent(@Valid @RequestBody ReqPageContentUpdatableDto reqPageContentUpdatableDto) {
        return ResponseEntityHelper.accepted(generalPageService.updatePageContent(reqPageContentUpdatableDto));
    }

    @GetMapping("/v1/getPageContentById/{id}")
    public ResponseEntity<ResPageContentUpdatableDto> getPageContentById(@PathVariable("id") Long id) {
        return ResponseEntityHelper.ok(generalPageService.getPageContentById(id));
    }

    @GetMapping("/v1/getAllPageContentByPageId/{pageId}")//todo refactor method name
    public ResponseEntity<List<ResPageContentUpdatableDto>> getAllPageContentByPageId(@PathVariable("pageId") Long pageId) {
        return ResponseEntityHelper.ok(generalPageService.getPageContentByPageId(pageId));
    }

    @GetMapping("/v1/getAllPageContentByPageCode/{pageCode}")//todo refactor method name
    public ResponseEntity<List<ResPageContentUpdatableDto>> getAllPageContentByPageCode(@PathVariable("pageCode") String pageCode) {
        return ResponseEntityHelper.ok(generalPageService.getPageContentByPageCode(pageCode));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deletePageContentById/{id}")
    public ResponseEntity<ResDeletedDto> deletePageContentById(@PathVariable("id") Long id) {
        generalPageService.deletePageContentById(id);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(id,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllPageContentByPageId/{pageId}")
    public ResponseEntity<ResDeletedDto> deleteAllPageContentByPageId(@PathVariable("pageId") Long pageId) {
        generalPageService.deleteAllPageContentByPageId(pageId);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(pageId,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllPageContentByPageCode/{pageCode}")
    public ResponseEntity<ResDeletedDto> deleteAllPageContentByPageCode(@PathVariable("pageCode") String pageCode) {
        generalPageService.deleteAllPageContentByPageCode(pageCode);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(pageCode,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/addMedia")
    public ResponseEntity<ResMediaDto> addMedia(@Valid @RequestBody ReqMediaDto reqMediaDto) {
        return ResponseEntityHelper.created(generalPageService.addMedia(reqMediaDto));
    }

    //todo @nader add update,delete,read Media and refactor Page Controller
}
