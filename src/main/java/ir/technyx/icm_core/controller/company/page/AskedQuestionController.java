package ir.technyx.icm_core.controller.company.page;

import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDto;
import ir.technyx.icm_core.dto.company.page.faq.request.ReqAskedQuestionItemDtoUpdatableDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionDetailDto;
import ir.technyx.icm_core.dto.company.page.faq.response.ResAskedQuestionItem;
import ir.technyx.icm_core.service.company.page.faq.AskedQuestionService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/faq")
@AllArgsConstructor
public class AskedQuestionController {

    private final AskedQuestionService askedQuestionService;

    private final MessageUtil messageUtil;


    //todo save(list[ReqAskedQuestionItemDto] felan anjam nashavad


    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','write')")
    @PostMapping("/v1/saveAskedQuestion")
    public ResponseEntity<ResAskedQuestionItem> saveAskedQuestion(
            @Valid @RequestBody ReqAskedQuestionItemDto reqAskedQuestionItemDto) {
        return ResponseEntityHelper.created(askedQuestionService.saveAskedQuestion(reqAskedQuestionItemDto));
    }

    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','write')")
    @PutMapping("/v1/updateAskedQuestion")
    public ResponseEntity<ResAskedQuestionItem> updateAskedQuestion(
            @Valid @RequestBody ReqAskedQuestionItemDtoUpdatableDto reqAskedQuestionItemUpdatableDto) {
        return ResponseEntityHelper.accepted(askedQuestionService.updateAskedQuestion(reqAskedQuestionItemUpdatableDto));
    }

    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAskedQuestion/{askedQuestionId}")
    public ResponseEntity<ResAskedQuestionDetailDto> getAskedQuestion(
            @PathVariable("askedQuestionId") Long askedQuestionId) {
        return ResponseEntityHelper.ok(askedQuestionService.getAskedQuestion(askedQuestionId));
    }

    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllAskedQuestionsByCompany/{companyCode}")
    public ResponseEntity<List<ResAskedQuestionDetailDto>> getAllAskedQuestionsByCompany(
            @PathVariable("companyCode") String companyCode) {
        return ResponseEntityHelper.ok(askedQuestionService.getAllAskedQuestionsByCompany(companyCode));
    }

    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAskedQuestion/{id}")
    public ResponseEntity<ResDeletedDto> deleteAskedQuestion(@PathVariable("id") Long id) {
        askedQuestionService.deleteAskedQuestion(id);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(id,
                        messageUtil.getLocalizedMessage("askedQuestion.deleteAskedQuestion")));
    }

    @PreAuthorize("hasRole('ROLE_CU_ADMIN') and hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllAskedQuestionByCompany/{companyCode}")
    public ResponseEntity<ResDeletedDto> deleteAllAskedQuestionByCompany(@PathVariable("companyCode") String companyCode) {
        askedQuestionService.deleteAllAskedQuestionByCompany(companyCode);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstanceByMessage(
                        messageUtil.getLocalizedMessage("askedQuestion.deleteAllAskedQuestionByCompany")));
    }

}
