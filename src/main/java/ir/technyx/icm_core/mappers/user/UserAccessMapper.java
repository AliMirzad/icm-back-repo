package ir.technyx.icm_core.mappers.user;

import ir.technyx.icm_core.domain.company.menu.Menu;
import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.domain.user.UserAccess;
import ir.technyx.icm_core.dto.common.response.ResRoleDetailsDto;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuAccessLevelItemDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuAccessLevelItemDto;
import ir.technyx.icm_core.dto.user.accessManagement.request.ReqUserAccessDto;
import ir.technyx.icm_core.dto.user.accessManagement.response.ResUserRolesDto;

import java.util.List;
import java.util.stream.Collectors;

public interface UserAccessMapper {

    static List<ResMenuAccessLevelDto> toMenuAccessLevelDtoList(List<MenuAccessLevel> menuAccessLevels) {
        return menuAccessLevels.stream().map(mal -> {
                    ResMenuAccessLevelDto resMenuAccessLevelDto = new ResMenuAccessLevelDto(
                            mal.getId(),
                            mal.getUserAccess().getUser().getId(),
                            mal.getUserAccess().getRole().getId(),
                            mal.getMenu().getUrl()
                    );
                    resMenuAccessLevelDto.setWrite(mal.isWrite());
                    resMenuAccessLevelDto.setRead(mal.isRead());
                    resMenuAccessLevelDto.setUpdate(mal.isUpdate());
                    resMenuAccessLevelDto.setDelete(mal.isDelete());
                    return resMenuAccessLevelDto;
                }
        ).collect(Collectors.toList());
    }

    static List<ResMenuAccessLevelItemDto> toResMenuAccessLevelItemDto(List<MenuAccessLevel> menuAccessLevels) {
        return menuAccessLevels.stream().map(UserAccessMapper::toResMenuAccessLevelItemDto).collect(Collectors.toList());
    }

    static ResMenuAccessLevelItemDto toResMenuAccessLevelItemDto(MenuAccessLevel menuAccessLevel) {
        ResMenuAccessLevelItemDto resMenuAccessLevelItemDto =
                new ResMenuAccessLevelItemDto(
                        menuAccessLevel.getId(),
                        menuAccessLevel.getMenu().getId(),
                        menuAccessLevel.getMenu().getUrl()
                );
        resMenuAccessLevelItemDto.setWrite(menuAccessLevel.isWrite());
        resMenuAccessLevelItemDto.setRead(menuAccessLevel.isRead());
        resMenuAccessLevelItemDto.setUpdate(menuAccessLevel.isUpdate());
        resMenuAccessLevelItemDto.setDelete(menuAccessLevel.isDelete());
        return resMenuAccessLevelItemDto;
    }

    static UserAccess toUserAccess(ReqUserAccessDto reqUserAccessDto) {
        return new UserAccess(
                new User(reqUserAccessDto.getUserId()),
                new Role(reqUserAccessDto.getRoleId()),
                reqUserAccessDto.getMenuAccessLevelItemDtoList().stream()
                        .map(UserAccessMapper::toMenuAccessLevel).collect(Collectors.toList()));

    }

    static MenuAccessLevel toMenuAccessLevel(ReqMenuAccessLevelItemDto reqMenuAccessLevelItemDto) {
        MenuAccessLevel menuAccessLevel = new MenuAccessLevel();
        menuAccessLevel.setMenu(new Menu(reqMenuAccessLevelItemDto.getMenuId()));
        menuAccessLevel.setWrite(reqMenuAccessLevelItemDto.isWrite());
        menuAccessLevel.setRead(reqMenuAccessLevelItemDto.isRead());
        menuAccessLevel.setUpdate(reqMenuAccessLevelItemDto.isUpdate());
        menuAccessLevel.setDelete(reqMenuAccessLevelItemDto.isDelete());
        return menuAccessLevel;
    }


    static ResUserRolesDto toResUserRolesDto(List<UserAccess> userAccesses) {
        User user = userAccesses.stream().findFirst().orElseThrow().getUser();

        ResUserRolesDto resUserRolesDto = new ResUserRolesDto();
        resUserRolesDto.setResRoleDetailsDtoList(userAccesses.stream().map(ua ->
                new ResRoleDetailsDto(ua.getRole().getId(), ua.getRole().getName(), ua.getRole().isActive())
        ).collect(Collectors.toList()));

        resUserRolesDto.setUserId(user.getId());
        resUserRolesDto.setUsername(user.getUsername());
        return resUserRolesDto;
    }


}
