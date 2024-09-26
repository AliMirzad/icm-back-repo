package ir.technyx.icm_core.controller.company.page.maintenance;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.req.ReqTicketUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResFeedbackUpdatableDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketDto;
import ir.technyx.icm_core.dto.company.page.maintenance.res.ResTicketUpdatableDto;
import ir.technyx.icm_core.service.company.page.maintenance.TicketService;
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
@RequestMapping("/icm/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final MessageUtil messageUtil;

    @Secured({Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/registerTicket")
    public ResponseEntity<ResTicketDto> registerTicket(@Valid @RequestBody ReqTicketDto reqTicketDto) {
        return ResponseEntityHelper.created(ticketService.registerTicket(reqTicketDto));
    }

    @Secured({Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updateTicket")
    public ResponseEntity<ResTicketUpdatableDto> updateTicket(@Valid @RequestBody
                                                              ReqTicketUpdatableDto reqTicketUpdatableDto) {

        return ResponseEntityHelper.accepted(ticketService.updateTicket(reqTicketUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteTicket/{ticketId}")
    public ResponseEntity<ResDeletedDto> deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(ticketId, messageUtil.getLocalizedMessage("user.deleteIcmUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllTicketByCompanyId/{companyId}")
    public ResponseEntity<ResDeletedDto> deleteAllTicketByCompanyId(@PathVariable("companyId") Long companyId) {
        ticketService.deleteAllTicketByCompanyId(companyId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(companyId, messageUtil.getLocalizedMessage("user.deleteIcmUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getTicketById/{ticketId}")
    public ResponseEntity<ResTicketUpdatableDto> getTicketById(@PathVariable("ticketId") Long ticketId) {
        return ResponseEntityHelper.ok(ticketService.getTicketById(ticketId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getTicketByCode/{code}")
    public ResponseEntity<ResTicketUpdatableDto> getTicketByCode(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(ticketService.getTicketByCode(code));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllTicketByCompanyId/{companyId}")
    public ResponseEntity<List<ResTicketUpdatableDto>> getAllTicketByCompanyId(@PathVariable("companyId") Long companyId) {
        return ResponseEntityHelper.ok(ticketService.getAllTicketByCompanyId(companyId));
    }

    @Secured({Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/registerFeedback")
    public ResponseEntity<ResFeedbackDto> registerFeedback(@Valid @RequestBody ReqFeedbackDto reqFeedbackDto) {
        return ResponseEntityHelper.created(ticketService.registerFeedback(reqFeedbackDto));
    }

    @Secured({Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updateFeedback")
    public ResponseEntity<ResFeedbackUpdatableDto> updateFeedback(@Valid @RequestBody
                                                                  ReqFeedbackUpdatableDto reqFeedbackUpdatableDto) {

        return ResponseEntityHelper.accepted(ticketService.updateFeedback(reqFeedbackUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteFeedback/{feedbackId}")
    public ResponseEntity<ResDeletedDto> deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        ticketService.deleteFeedback(feedbackId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(feedbackId, messageUtil.getLocalizedMessage("user.deleteIcmUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteAllFeedbackByTicketId/{ticketId}")
    public ResponseEntity<ResDeletedDto> deleteAllFeedbackByTicketId(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteAllFeedbackByTicketId(ticketId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(ticketId, messageUtil.getLocalizedMessage("user.deleteIcmUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getFeedbackById/{feedbackId}")
    public ResponseEntity<ResFeedbackUpdatableDto> getFeedbackById(@PathVariable("feedbackId") Long feedbackId) {
        return ResponseEntityHelper.ok(ticketService.getFeedbackById(feedbackId));
    }


    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN, Role.ROLE_CU_MEMBER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllFeedbackByTicketId/{ticketId}")
    public ResponseEntity<List<ResFeedbackUpdatableDto>> getAllFeedbackByTicketId(@PathVariable("ticketId") Long ticketId) {
        return ResponseEntityHelper.ok(ticketService.getAllFeedbackByTicketId(ticketId));
    }

}
