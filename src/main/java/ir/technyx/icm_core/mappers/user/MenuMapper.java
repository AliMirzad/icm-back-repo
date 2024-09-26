package ir.technyx.icm_core.mappers.user;

import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.menu.Menu;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuDto;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuUpdatableDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDetailsDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDto;

import java.util.List;
import java.util.stream.Collectors;

public interface MenuMapper {

    static Menu toMenu(Long menuId) {
        return new Menu(menuId);
    }


    static Menu toMenu(ReqMenuDto reqMenuDto) {
        return new Menu(
                new Company(reqMenuDto.getCompanyId()),
                reqMenuDto.getTitle(),
                reqMenuDto.getUrl(),
                reqMenuDto.getIconPath(),
                reqMenuDto.isActive());
    }

    static Menu toMenu(ReqMenuUpdatableDto reqMenuUpdatableDto) {
        return new Menu(
                reqMenuUpdatableDto.getId(),
                new Company(reqMenuUpdatableDto.getCompanyId()),
                reqMenuUpdatableDto.getTitle(),
                reqMenuUpdatableDto.getUrl(),
                reqMenuUpdatableDto.getIconPath(),
                reqMenuUpdatableDto.isActive()
        );
    }


    static ResMenuDto toResMenuDto(Menu menu) {
        return new ResMenuDto(menu.getId(), menu.getTitle(), menu.getCompany().getName());
    }

    static List<ResMenuDetailsDto> toResMenuDetailsDto(List<Menu> menus) {
        return menus.stream().map(MenuMapper::toResMenuDetailsDto).collect(Collectors.toList());
    }

    static ResMenuDetailsDto toResMenuDetailsDto(Menu menu) {
        return new ResMenuDetailsDto(menu.getId(),
                menu.getCompany().getName(),
                menu.getTitle(),
                menu.getUrl(),
                menu.getIconPath(),
                menu.isActive()
        );
    }

}
