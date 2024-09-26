package ir.technyx.icm_core.controller.common;

import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeDto;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeUpdatableDto;
import ir.technyx.icm_core.dto.common.response.*;
import ir.technyx.icm_core.service.common.BaseInfoService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/icm/baseInfo")
@AllArgsConstructor
public class BaseInfoController {

    private final BaseInfoService baseInfoService;

    private final MessageUtil messageUtil;

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @GetMapping("/v1/getIcmRoles")
    public ResponseEntity<List<ResRoleDetailsDto>> getIcmRoles() {
        return ResponseEntityHelper.ok(baseInfoService.getIcmRoles());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getRoles")
    public ResponseEntity<List<ResRoleDetailsDto>> getRoles() {
        return ResponseEntityHelper.ok(baseInfoService.getRoles());
    }

    @GetMapping("/v1/getTypesByCode/{code}")
    public ResponseEntity<List<ResManagementTypeForListDto>> getTypesByCode(@PathVariable("code") String code) {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(code));
    }

    @GetMapping("/v1/getLocationTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getLocationTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.LOCATION_TYPE));
    }

    @GetMapping("/v1/getAllLocationInfo")
    public ResponseEntity<List<ResLocationInfoDto>> getAllLocationInfo() {
        return ResponseEntityHelper.ok(baseInfoService.getAllLocationInfo());
    }

    @GetMapping("/v1/getGenderTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getGenderTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.GENDER));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getAccessLevelTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getAccessLevelTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.ACCESS_TYPE));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getPageTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getPageTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.SPECIALIZED_PAGE_TYPE));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getContentTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getContentTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.CONTENT_TYPE));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @GetMapping("/v1/getFileTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getFileTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.FILE_TYPE));
    }


    @GetMapping("/v1/getAdditionalInfoTypes")
    public ResponseEntity<List<ResManagementTypeForListDto>> getInfoTypes() {
        return ResponseEntityHelper.ok(baseInfoService.getManagementType(ManagementType.ADDITIONAL_INFO));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PostMapping("/v1/registerManagementType")
    public ResponseEntity<ResManagementTypeDto> registerManagementType(
            @Valid @RequestBody ReqManagementTypeDto reqManagementTypeDto) {
        return ResponseEntityHelper.created(baseInfoService.save(reqManagementTypeDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PostMapping("/v1/registerManagementTypes")
    public ResponseEntity<List<ResManagementTypeDto>> registerManagementTypes(
            @Valid @RequestBody List<ReqManagementTypeDto> reqManagementTypeDtoList) {
        return ResponseEntityHelper.created(baseInfoService.save(reqManagementTypeDtoList));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PutMapping("/v1/updateManagementType")
    public ResponseEntity<ResManagementTypeDto> updateManagementType(
            @Valid @RequestBody ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto) {
        return ResponseEntityHelper.accepted(baseInfoService.update(reqManagementTypeUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PutMapping("/v1/updateManagementTypes")
    public ResponseEntity<List<ResManagementTypeDto>> updateManagementTypes(
            @Valid @RequestBody List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList) {

        return ResponseEntityHelper.accepted(baseInfoService.update(reqManagementTypeUpdatableDtoList));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @DeleteMapping("/v1/deleteManagementType/{id}")
    public ResponseEntity<ResDeletedDto> deleteManagementType(@PathVariable(name = "id") Long id) {
        baseInfoService.delete(id);
        return ResponseEntityHelper.accepted(ResDeletedDto
                .getInstance(id, messageUtil.getLocalizedMessage("managementType.deleteManagementType")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @DeleteMapping("/v1/deleteAllManagementTypes")
    public ResponseEntity<ResDeletedDto> deleteAllManagementTypes() {
        baseInfoService.deleteAll();
        return ResponseEntityHelper.accepted(ResDeletedDto.getInstanceByMessage(
                messageUtil.getLocalizedMessage("managementType.deleteAllManagementTypes")));
    }


}
