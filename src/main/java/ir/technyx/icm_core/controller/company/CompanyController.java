package ir.technyx.icm_core.controller.company;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResBaseInfoDto;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyDto;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.request.ReqSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyListDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.response.ResSubscriptionPlanCompanyDto;
import ir.technyx.icm_core.dto.company.page.common.request.ReqCompanyPageDto;
import ir.technyx.icm_core.dto.company.page.common.response.ResPageStatusDto;
import ir.technyx.icm_core.service.company.CompanyService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final MessageUtil messageUtil;

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/saveCompany")
    public ResponseEntity<ResCompanyDto> saveCompany(@RequestBody ReqCompanyDto reqCompanyDto) {
        return ResponseEntityHelper.created(companyService.save(reqCompanyDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PutMapping("/v1/updateCompany")
    public ResponseEntity<ResCompanyUpdatableDto> updateCompany(@RequestBody ReqCompanyUpdatableDto reqCompanyUpdatableDto) {
        return ResponseEntityHelper.accepted(companyService.update(reqCompanyUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getCompanyById/{id}")
    public ResponseEntity<ResCompanyUpdatableDto> getCompany(@PathVariable("id") Long id) {
        return ResponseEntityHelper.ok(companyService.getCompany(id));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getCompanyByCode/{code}")
    public ResponseEntity<ResCompanyUpdatableDto> getCompany(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(companyService.getCompany(code));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllCompany")
    public ResponseEntity<List<ResCompanyListDto>> getAllCompany() {
        return ResponseEntityHelper.ok(companyService.getAllCompany());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteCompanyById/{id}")
    public ResponseEntity<ResDeletedDto> deleteCompany(@PathVariable("id") Long id) {
        companyService.delete(id);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(id,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteCompanyById/{code}")
    public ResponseEntity<ResDeletedDto> deleteCompany(@PathVariable("code") String code) {
        companyService.delete(code);
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstance(code,
                messageUtil.getLocalizedMessage("company.deleteCompany")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/activeSubscriptionPlanForCompany")
    public ResponseEntity<ResSubscriptionPlanCompanyDto> activeSubscriptionPlanForCompany(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto) {
        return ResponseEntityHelper.accepted(companyService.activeSubscriptionPlanForCompany(reqSubscriptionPlanCompanyDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @PostMapping("/v1/addDefaultPages")
    public ResponseEntity<ResBaseInfoDto> addDefaultPages(ReqSubscriptionPlanCompanyDto reqSubscriptionPlanCompanyDto) {
        companyService.addDefaultPages(reqSubscriptionPlanCompanyDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @PostMapping("/v1/getAvailablePages/{subscriptionPlanId}")
    public ResponseEntity<List<ResPageStatusDto>> getAvailablePages(@PathVariable("subscriptionPlanId") Long subscriptionPlanId) {
        return ResponseEntityHelper.ok(companyService.availablePages(subscriptionPlanId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PostMapping("/v1/changePageStatus")
    public ResponseEntity<ResBaseInfoDto> changePageStatus(@RequestBody ReqCompanyPageDto reqCompanyPageDto) {
        companyService.changePageStatus(reqCompanyPageDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

}
