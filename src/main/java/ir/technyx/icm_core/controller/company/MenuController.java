package ir.technyx.icm_core.controller.company;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDetailsDto;
import ir.technyx.icm_core.dto.company.menu.response.ResSiteMapDto;
import ir.technyx.icm_core.service.company.menu.MenuService;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/icm/menuController")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/v1/getMenuById/{menuId}")
    public ResponseEntity<ResMenuDetailsDto> getMenuById(@PathVariable("menuId") Long menuId) {
        return ResponseEntityHelper.ok(menuService.getMenuById(menuId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllIcmMenu")
    public ResponseEntity<List<ResMenuDetailsDto>> getAllIcmMenu() {
        return ResponseEntityHelper.ok(menuService.getAllIcmMenu());
    }

    @GetMapping("/v1/getAllMenuByCompanyCode/{code}")
    public ResponseEntity<List<ResMenuDetailsDto>> getAllMenuByCompanyCode(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(menuService.getAllMenuByCompanyCode(code));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getSiteMap/{companyCode}")
    public ResponseEntity<ResSiteMapDto> getSiteMap(@PathVariable("companyCode") String companyCode) {
        return ResponseEntityHelper.ok(menuService.getSiteMap(companyCode));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllSiteMap/{companyCode}")
    public ResponseEntity<List<ResSiteMapDto>> getAllSiteMap(@PathVariable("companyCode") String companyCode) {
        return ResponseEntityHelper.ok(menuService.getAllSiteMap(companyCode));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllSiteMap")
    public ResponseEntity<List<ResSiteMapDto>> getAllSiteMap() {
        return ResponseEntityHelper.ok(menuService.getAllSiteMap());
    }

}
