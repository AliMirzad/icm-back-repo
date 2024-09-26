package ir.technyx.icm_core.service.user;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.menu.Menu;
import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.UserAccess;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelItemDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserAccessDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserRolesDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResUserRolesDto;
import ir.technyx.icm_core.mappers.user.UserAccessMapper;
import ir.technyx.icm_core.mappers.user.UserMapper;
import ir.technyx.icm_core.repository.company.menu.MenuAccessLevelRepository;
import ir.technyx.icm_core.repository.company.menu.MenuRepository;
import ir.technyx.icm_core.repository.user.RoleRepository;
import ir.technyx.icm_core.repository.user.UserAccessRepository;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAccessServiceImpl implements UserAccessService {

    private final UserAccessRepository userAccessRepository;
    private final RoleRepository roleRepository;
    private final MenuAccessLevelRepository menuAccessLevelRepository;
    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public ResUserRolesDto getIcmUserRoles(Long userId) {
        List<UserAccess> userAccesses = userAccessRepository.getIcmUserRoles(userId);
        return UserAccessMapper.toResUserRolesDto(userAccesses);
    }

    @Override
    @Transactional
    public void setIcmUserRoles(ReqUserRolesDto reqUserRolesDto) {
        List<UserAccess> userAccessList = UserMapper.toUserAccessList(reqUserRolesDto);
        validateIcmAdminRole(userAccessList);
        userAccessRepository.saveAll(userAccessList);
    }

    @Override
    @Transactional
    public void deleteAllIcmUserRoles(Long userId) {
        validateHaveIcmUserRole();
        userAccessRepository.deleteAllByUser_Id(userId);
    }

    private void validateIcmAdminRole(List<UserAccess> userAccessList) {
        Role icmAdminRole = roleRepository.findByName(Role.ROLE_ICM_ADMIN).orElseThrow(NullPointerException::new);

        if (userAccessList.stream()
                .anyMatch(ual -> ual.getRole().getId().equals(icmAdminRole.getId()))) {
            throw new IllegalArgumentException("accessManagement.canNotSaveIcmAdminRole ");
        }
    }

    @Override
    @Transactional
    public ResUserRolesDto getUserRoles(Long userId) {
        List<UserAccess> userAccesses = userAccessRepository.getUserRoles(userId);
        return UserAccessMapper.toResUserRolesDto(userAccesses);
    }

    @Override
    @Transactional
    public void setUserRoles(ReqUserRolesDto reqUserRolesDto) {
        List<UserAccess> userAccessList = UserMapper.toUserAccessList(reqUserRolesDto);
        validateOtherRoles(userAccessList);
        userAccessRepository.saveAll(userAccessList);
    }

    private void validateOtherRoles(List<UserAccess> userAccessList) {
        List<Role> roles = roleRepository.findAllByNames(Role.ROLE_ICM_ADMIN, Role.ROLE_ICM_USER, Role.ROLE_CU_ADMIN);
        if (userAccessList.stream().anyMatch(ual ->
                roles.stream().anyMatch(r -> ual.getRole().getId().equals(r.getId()))
        )) {
            throw new IllegalArgumentException("accessManagement.canNotSaveIcmRoles");
        }
    }

    @Override
    @Transactional
    public void deleteAllUserRoles(Long userId) {
        validateHaveIcmOrAdminUserRole();
        userAccessRepository.deleteAllByUser_Id(userId);
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelDto> getIcmMenuAccessLevelList() {
        return UserAccessMapper.toMenuAccessLevelDtoList(menuAccessLevelRepository
                .findAllIcmMenu().orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelDto> getMenuAccessLevelListByCompanyId(Long companyId) {
        return UserAccessMapper.toMenuAccessLevelDtoList(menuAccessLevelRepository
                .findAllMenuByCompanyId(companyId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelDto> getMenuAccessLevelListByUserId(Long userId) {
        return UserAccessMapper.toMenuAccessLevelDtoList(menuAccessLevelRepository
                .findAllMenuByUserId(userId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public void setIcmAccessLevels(ReqUserAccessDto reqUserAccessDto) {//todo @nader: test this method
        Long oldUserAccessId = userAccessRepository
                .findByUserIdAndRoleId(reqUserAccessDto.getUserId(), reqUserAccessDto.getRoleId())
                .orElseThrow(NullPointerException::new).getId();

        UserAccess userAccess = UserAccessMapper.toUserAccess(reqUserAccessDto);
        userAccess.setId(oldUserAccessId);
        userAccessRepository.save(userAccess);
    }

    @Override
    @Transactional
    public void deleteAllIcmAccessLevels(Long userId) {
        validateHaveIcmUserRole();
        userAccessRepository.deleteAllByUser_Id(userId);
    }

    private void validateHaveIcmUserRole() {
        if (!UserUtility.haveIcmAdminRole()) {
            throw new AccessDeniedException("security.accessDeniedException");
        }
    }

    @Override
    @Transactional
    public void setAccessLevels(ReqUserAccessDto reqUserAccessDto) {
        UserAccess userAccess = UserAccessMapper.toUserAccess(reqUserAccessDto);
        userAccessRepository.save(userAccess);
    }

    @Override
    @Transactional
    public void deleteAllAccessLevels(Long userId) {
        validateHaveIcmOrAdminUserRole();
        userAccessRepository.deleteAllByUser_Id(userId);
    }

    private void validateHaveIcmOrAdminUserRole() {
        if (!UserUtility.haveIcmAdminRole()
                && !UserUtility.haveIcmUserRole()
                && !UserUtility.haveCuAdminRole()) {
            throw new AccessDeniedException("security.accessDeniedException");
        }
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelItemDto> getAllIcmUserMenuAccessLevels(Long userId) {
        return UserAccessMapper.toResMenuAccessLevelItemDto(menuAccessLevelRepository
                .findAllIcmMenuByUserId(userId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelItemDto> getAllIcmUserMenuAccessLevels(Long userId, Long roleId) {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(Company.ICM_COMPANY_CODE).orElseThrow(NullPointerException::new);
        return delegateAllPermissions(menus,userId,roleId);
    }

    private List<Menu> filterMenu(List<Menu> menus, List<MenuAccessLevel> menuAccessLevels) {
        if (menuAccessLevels.isEmpty()) { return menus; }
        return menus.stream()
                .filter(m -> menuAccessLevels.stream()
                        .noneMatch(ma -> m.getId().equals(ma.getMenu().getId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelItemDto> getAllUserMenuAccessLevels(Long userId) {
        return UserAccessMapper.toResMenuAccessLevelItemDto(menuAccessLevelRepository
                .findAllMenuByUserId(userId).orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelItemDto> getAllUserMenuAccessLevels(String companyCode, Long userId, Long roleId) {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(companyCode).orElseThrow(NullPointerException::new);
        return delegateAllPermissions(menus,userId,roleId);
    }

    private List<ResMenuAccessLevelItemDto> delegateAllPermissions( List<Menu> menus,Long userId, Long roleId){
        List<MenuAccessLevel> menuAccessLevels = menuAccessLevelRepository
                .findAllIcmMenuByUserIdAndRoleId(userId, roleId).orElseThrow(NullPointerException::new);
        menus = filterMenu(menus, menuAccessLevels);
        menus.forEach(m -> menuAccessLevels.add(new MenuAccessLevel(m, true)));
        return UserAccessMapper.toResMenuAccessLevelItemDto(menuAccessLevels);
    }

    @Override
    @Transactional
    public List<ResMenuAccessLevelItemDto> getAllIcmMenuAccessLevels() {
        return UserAccessMapper.toResMenuAccessLevelItemDto(menuAccessLevelRepository
                .getAllIcmMenuAccessLevels().orElseThrow(NullPointerException::new));
    }

}
