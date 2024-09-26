package ir.technyx.icm_core.controller.company.subscription;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.request.ReqSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanDto;
import ir.technyx.icm_core.dto.company.subscription.response.ResSubscriptionPlanUpdatableDto;
import ir.technyx.icm_core.service.company.subscription.SubscriptionPlanService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/company/subscriptionPlan")
@AllArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    private final MessageUtil messageUtil;

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/saveSubscriptionPlan")
    public ResponseEntity<ResSubscriptionPlanDto>
    saveSubscriptionPlan(@RequestBody ReqSubscriptionPlanDto reqSubscriptionPlanDto) {

        return ResponseEntityHelper.created(subscriptionPlanService.saveSubscriptionPlan(reqSubscriptionPlanDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PutMapping("/v1/updateSubscriptionPlan")
    public ResponseEntity<ResSubscriptionPlanUpdatableDto>
    updateSubscriptionPlan(@RequestBody ReqSubscriptionPlanUpdatableDto reqSubscriptionPlanUpdatableDto) {

        return ResponseEntityHelper
                .accepted(subscriptionPlanService.updateSubscriptionPlan(reqSubscriptionPlanUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getSubscriptionPlanById/{id}")
    public ResponseEntity<ResSubscriptionPlanUpdatableDto> getSubscriptionPlanById(@PathVariable("id") Long id) {
        return ResponseEntityHelper.ok(subscriptionPlanService.getSubscriptionPlanById(id));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getSubscriptionPlanByCode/{code}")
    public ResponseEntity<ResSubscriptionPlanUpdatableDto> getSubscriptionPlanByCode(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(subscriptionPlanService.getSubscriptionPlanByCode(code));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllSubscriptionPlan")
    public ResponseEntity<List<ResSubscriptionPlanUpdatableDto>> getAllSubscriptionPlan() {
        return ResponseEntityHelper.ok(subscriptionPlanService.getAllSubscriptionPlan());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteSubscriptionPlanById/{id}")
    public ResponseEntity<ResDeletedDto> deleteSubscriptionPlanById(@PathVariable("id") Long id) {
        subscriptionPlanService.delete(id);
        return ResponseEntityHelper.accepted(
                ResDeletedDto
                        .getInstance(id, messageUtil.getLocalizedMessage("subscription.deleteSubscription")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteSubscriptionPlanByCode/{code}")
    public ResponseEntity<ResDeletedDto> deleteSubscriptionPlanByCode(@PathVariable("code") String code) {
        subscriptionPlanService.delete(code);
        return ResponseEntityHelper.accepted(
                ResDeletedDto
                        .getInstance(code, messageUtil.getLocalizedMessage("subscription.deleteSubscription")));
    }
}
