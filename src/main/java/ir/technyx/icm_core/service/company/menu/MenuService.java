package ir.technyx.icm_core.service.company.menu;

import ir.technyx.icm_core.dto.company.menu.request.ReqMenuDto;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuUpdatableDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDetailsDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDto;
import ir.technyx.icm_core.dto.company.menu.response.ResSiteMapDto;

import java.util.List;

public interface MenuService {

    ResMenuDto addMenu(ReqMenuDto reqMenuDto);

    ResMenuDto updateMenu(ReqMenuUpdatableDto reqMenuUpdatableDto);

    void deleteMenuById(Long id);

    void deleteAllMenuByCompanyId(Long companyId);

    ResMenuDetailsDto getMenuById(Long menuId);

    List<ResMenuDetailsDto> getAllIcmMenu();

    List<ResMenuDetailsDto> getAllMenuByCompanyCode(String code);

    ResSiteMapDto createSiteMap(String companyCode);

    ResSiteMapDto getSiteMap(String companyCode);

    List<ResSiteMapDto> getAllSiteMap(String companyCode);

    List<ResSiteMapDto> getAllSiteMap();


}
