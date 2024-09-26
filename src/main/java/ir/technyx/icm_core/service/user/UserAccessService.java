package ir.technyx.icm_core.service.user;

import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelItemDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserAccessDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserRolesDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResUserRolesDto;

import java.util.List;

public interface UserAccessService {

    ResUserRolesDto getIcmUserRoles(Long userId);

    void setIcmUserRoles(ReqUserRolesDto reqUserRolesDto);

    void deleteAllIcmUserRoles(Long userId);

    ResUserRolesDto getUserRoles(Long userId);

    void setUserRoles(ReqUserRolesDto reqUserRolesDto);

    void deleteAllUserRoles(Long userId);

    List<ResMenuAccessLevelDto> getIcmMenuAccessLevelList();

    List<ResMenuAccessLevelDto> getMenuAccessLevelListByCompanyId(Long companyId);

    List<ResMenuAccessLevelDto> getMenuAccessLevelListByUserId(Long userId);

    void setIcmAccessLevels(ReqUserAccessDto reqUserAccessDto);

    void deleteAllIcmAccessLevels(Long userId);

    void setAccessLevels(ReqUserAccessDto reqUserAccessDto);

    void deleteAllAccessLevels(Long userId);

    List<ResMenuAccessLevelItemDto> getAllIcmUserMenuAccessLevels(Long userId);

    List<ResMenuAccessLevelItemDto> getAllIcmUserMenuAccessLevels(Long userId, Long roleId);

    List<ResMenuAccessLevelItemDto> getAllIcmMenuAccessLevels();

    List<ResMenuAccessLevelItemDto> getAllUserMenuAccessLevels(Long userId);

    List<ResMenuAccessLevelItemDto> getAllUserMenuAccessLevels(String companyCode, Long userId, Long roleId);
}
