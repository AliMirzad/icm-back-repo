package ir.technyx.icm_core.controller.user;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResBaseInfoDto;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelItemDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserAccessDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserRolesDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResUserRolesDto;
import ir.technyx.icm_core.service.user.UserAccessService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/accessManagement")
@AllArgsConstructor
public class AccessManagementController {

    private final UserAccessService userAccessService;

    private final MessageUtil messageUtil;


    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getIcmUserRoles/{userId}")
    public ResponseEntity<ResUserRolesDto> getIcmUserRoles(@PathVariable("userId") Long userId) {
        return ResponseEntityHelper.ok(userAccessService.getIcmUserRoles(userId));
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @PostMapping("/v1/setIcmUserRoles")
    public ResponseEntity<ResBaseInfoDto> setIcmUserRoles(@Valid @RequestBody ReqUserRolesDto reqUserRolesDto) {
        userAccessService.setIcmUserRoles(reqUserRolesDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @DeleteMapping("/v1/deleteIcmUserRoles/{userId}")
    public ResponseEntity<ResDeletedDto> deleteIcmUserRoles(@PathVariable("userId") Long userId) {
        userAccessService.deleteAllIcmUserRoles(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(),
                messageUtil.getLocalizedMessage("accessManagement.deleteIcmUserRoles"));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getUserRoles/{userId}")
    public ResponseEntity<ResUserRolesDto> getUserRoles(@PathVariable("userId") Long userId) {
        return ResponseEntityHelper.ok(userAccessService.getUserRoles(userId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PostMapping("/v1/setUserRoles")
    public ResponseEntity<ResBaseInfoDto> setUserRoles(@Valid @RequestBody ReqUserRolesDto reqUserRolesDto) {
        userAccessService.setUserRoles(reqUserRolesDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @DeleteMapping("/v1/deleteAllUserRoles/{userId}")
    public ResponseEntity<ResDeletedDto> deleteAllUserRoles(@PathVariable("userId") Long userId) {
        userAccessService.deleteAllUserRoles(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(),
                messageUtil.getLocalizedMessage("accessManagement.deleteAllUserRoles"));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAllIcmUserMenuAccessLevels/{userId}")
    public ResponseEntity<List<ResMenuAccessLevelItemDto>> getAllIcmUserMenuAccessLevels(@PathVariable("userId") Long userId) {
        return ResponseEntityHelper.ok(userAccessService.getAllIcmUserMenuAccessLevels(userId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAllIcmUserMenuAccessLevels/{userId}/{roleId}")
    public ResponseEntity<List<ResMenuAccessLevelItemDto>>
    getAllIcmUserMenuAccessLevels(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return ResponseEntityHelper.ok(userAccessService.getAllIcmUserMenuAccessLevels(userId, roleId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAllIcmMenuAccessLevels/")
    public ResponseEntity<List<ResMenuAccessLevelItemDto>> getAllIcmUserMenuAccessLevels() {
        return ResponseEntityHelper.ok(userAccessService.getAllIcmMenuAccessLevels());
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @PostMapping("/v1/setIcmAccessLevels")
    public ResponseEntity<ResBaseInfoDto> setIcmAccessLevels(@Valid @RequestBody ReqUserAccessDto reqUserAccessDto) {
        userAccessService.setIcmAccessLevels(reqUserAccessDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @DeleteMapping("/v1/deleteAllIcmAccessLevels/{userId}")
    public ResponseEntity<ResDeletedDto> deleteAllIcmAccessLevels(@PathVariable("userId") Long userId) {
        userAccessService.deleteAllIcmAccessLevels(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(),
                messageUtil.getLocalizedMessage("accessManagement.deleteAllIcmAccessLevels"));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAllUserMenuAccessLevels/{userId}")
    public ResponseEntity<List<ResMenuAccessLevelItemDto>> getAllUserMenuAccessLevels(
            @PathVariable("userId") Long userId) {
        return ResponseEntityHelper.ok(userAccessService.getAllUserMenuAccessLevels(userId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAllUserMenuAccessLevels/{companyCode}/{userId}/{roleId}")
    public ResponseEntity<List<ResMenuAccessLevelItemDto>> getAllUserMenuAccessLevels(
            @PathVariable("companyCode") String companyCode,
            @PathVariable("userId") Long userId,
            @PathVariable("roleId") Long roleId) {

        return ResponseEntityHelper.ok(userAccessService.getAllUserMenuAccessLevels(companyCode, userId, roleId));
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @PostMapping("/v1/setAccessLevels")
    public ResponseEntity<ResBaseInfoDto> setAccessLevels(@Valid @RequestBody ReqUserAccessDto reqUserAccessDto) {
        userAccessService.setAccessLevels(reqUserAccessDto);
        return ResponseEntityHelper.accepted(ResBaseInfoDto.getInstance());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @DeleteMapping("/v1/deleteAllAccessLevels/{userId}")
    public ResponseEntity<ResDeletedDto> deleteAllAccessLevels(@PathVariable("userId") Long userId) {
        userAccessService.deleteAllAccessLevels(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(),
                messageUtil.getLocalizedMessage("accessManagement.deleteAllAccessLevels"));
    }

}
