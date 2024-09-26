package ir.technyx.icm_core.controller.user;

import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResDeletedDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqAuthenticationDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResAuthenticationDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserDto;
import ir.technyx.icm_core.dto.user.icm.request.ReqIcmUserUpdatableDto;
import ir.technyx.icm_core.dto.user.icm.response.ResIcmUserDetailsDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserDto;
import ir.technyx.icm_core.dto.user.member.request.ReqUserUpdatableDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserDetail;
import ir.technyx.icm_core.dto.user.member.response.ResUserDto;
import ir.technyx.icm_core.dto.user.member.response.ResUserListDto;
import ir.technyx.icm_core.service.user.UserService;
import ir.technyx.icm_core.utils.MessageUtil;
import ir.technyx.icm_core.utils.ResponseEntityHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/icm/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final MessageUtil messageUtil;

    private static final Logger logger = LogManager.getLogger(UserController.class);


    @PostMapping("/login")
    public ResponseEntity<ResAuthenticationDto> login(@Valid @RequestBody ReqAuthenticationDto reqAuthenticationDto) {
        return ResponseEntityHelper.ok(userService.authenticate(reqAuthenticationDto));
    }

    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }


    @Secured(Role.ROLE_ICM_ADMIN)
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/registerIcmUser")
    public ResponseEntity<ResUserDto> registerIcmUser(@Valid @RequestBody ReqIcmUserDto reqIcmUserDto) {
        return ResponseEntityHelper.created(userService.registerUser(reqIcmUserDto));
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updateIcmUser")
    public ResponseEntity<ResIcmUserDetailsDto> updateIcmUser(@Valid @RequestBody
                                                              ReqIcmUserUpdatableDto reqIcmUserUpdatableDto) {

        return ResponseEntityHelper.accepted(userService.updateUser(reqIcmUserUpdatableDto));
    }

    @Secured(Role.ROLE_ICM_ADMIN)
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/deleteIcmUser/{userId}")
    public ResponseEntity<ResDeletedDto> deleteIcmUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(userId, messageUtil.getLocalizedMessage("user.deleteIcmUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getIcmUser/{userId}")
    public ResponseEntity<ResIcmUserDetailsDto> getIcmUser(@PathVariable("userId") Long userId) {
        logger.trace("We've just greeted the user!");
        logger.debug("We've just greeted the user!");
        logger.info("We've just greeted the user!");
        logger.warn("We've just greeted the user!");
        logger.error("We've just greeted the user!");
        logger.fatal("We've just greeted the user!");
        return ResponseEntityHelper.ok(userService.getIcmUser(userId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllIcmUser")
    public ResponseEntity<List<ResIcmUserDetailsDto>> getAllIcmUser() {
        return ResponseEntityHelper.ok(userService.getAllIcmUser());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','write')")
    @PostMapping("/v1/registerUser")
    public ResponseEntity<ResUserDto> registerUser(@Valid @RequestBody ReqUserDto reqUserDto) {
        return ResponseEntityHelper.created(userService.registerUser(reqUserDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','update')")
    @PutMapping("/v1/updateUser")
    public ResponseEntity<ResUserDetail> updateUser(@Valid @RequestBody ReqUserUpdatableDto reqUserUpdatableDto) {
        return ResponseEntityHelper.accepted(userService.updateUser(reqUserUpdatableDto));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','delete')")
    @DeleteMapping("/v1/delete/{userId}")
    public ResponseEntity<ResDeletedDto> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntityHelper.accepted(
                ResDeletedDto.getInstance(userId, messageUtil.getLocalizedMessage("user.deleteUser")));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getUser/{userId}")
    public ResponseEntity<ResUserDetail> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntityHelper.ok(userService.getUser(userId));
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllUser")
    public ResponseEntity<List<ResUserListDto>> getAllUser() {
        return ResponseEntityHelper.ok(userService.getAllUser());
    }

    @Secured({Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN})
    @PreAuthorize("hasPermission('hasAccess','read')")
    @GetMapping("/v1/getAllUserByCompany/{companyId}")
    public ResponseEntity<List<ResUserListDto>> getAllUserByCompany(@PathVariable("companyId") Long companyId) {
        return ResponseEntityHelper.ok(userService.getAllUserByCompany(companyId));
    }

}
