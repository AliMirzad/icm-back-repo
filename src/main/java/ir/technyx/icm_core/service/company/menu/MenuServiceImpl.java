package ir.technyx.icm_core.service.company.menu;

import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.menu.Menu;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuDto;
import ir.technyx.icm_core.dto.company.menu.request.ReqMenuUpdatableDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDetailsDto;
import ir.technyx.icm_core.dto.company.menu.response.ResMenuDto;
import ir.technyx.icm_core.dto.company.menu.response.ResSiteMapDto;
import ir.technyx.icm_core.mappers.user.MenuMapper;
import ir.technyx.icm_core.repository.company.menu.MenuRepository;
import ir.technyx.icm_core.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public ResMenuDto addMenu(ReqMenuDto reqMenuDto) {
        Menu menu = MenuMapper.toMenu(reqMenuDto);
        menu.setRegistrationInfo(UserUtility.getCurrentRegistrationInfo());
        menuRepository.save(menu);
        return MenuMapper.toResMenuDto(menu);
    }

    @Override
    @Transactional
    public ResMenuDto updateMenu(ReqMenuUpdatableDto reqMenuUpdatableDto) {
        Menu menu = MenuMapper.toMenu(reqMenuUpdatableDto);
        menu.setRegistrationInfo(new RegistrationInfo(UserUtility.getCurrentUserId(), LocalDateTime.now()));
        menuRepository.save(menu);
        return MenuMapper.toResMenuDto(menu);
    }

    @Override
    @Transactional
    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllMenuByCompanyId(Long companyId) {
        menuRepository.deleteAllMenuByCompanyId(companyId);
    }

    @Override
    @Transactional
    public ResMenuDetailsDto getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(NullPointerException::new);
        return MenuMapper.toResMenuDetailsDto(menu);
    }

    @Override
    @Transactional
    public List<ResMenuDetailsDto> getAllIcmMenu() {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(Company.ICM_COMPANY_CODE).orElseThrow(NullPointerException::new);
        return MenuMapper.toResMenuDetailsDto(menus);
    }

    @Override
    @Transactional
    public List<ResMenuDetailsDto> getAllMenuByCompanyCode(String code) {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(code).orElseThrow(NullPointerException::new);
        return MenuMapper.toResMenuDetailsDto(menus);
    }

    @Override
    public ResSiteMapDto createSiteMap(String companyCode) {


        return null;
    }

    @Override
    public ResSiteMapDto getSiteMap(String companyCode) {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(companyCode).orElseThrow(NullPointerException::new);
        List<SiteMapItem> siteMapItems = menus.stream().map(this::createSiteMapItem).toList();
        saveSiteMapFile(siteMapItems);
        return null;
    }

    @Override
    public List<ResSiteMapDto> getAllSiteMap(String companyCode) {
        return null;
    }

    @Override
    public List<ResSiteMapDto> getAllSiteMap() {
        return null;
    }

    private SiteMapItem createSiteMapItem(Menu menu) {
        return new SiteMapItem(menu.getUrl(), LocalDateTime.now().toString(), "Daily", String.valueOf(menu.getPriority()));
    }

    private void saveSiteMapFile(List<SiteMapItem> siteMapItems) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sitemap.xml"))) {
            writer.write("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
            for (SiteMapItem sim : siteMapItems) {
                writer.write("<url>");
                writer.write(
                        "<loc>" + sim.loc() + "</loc>"
                                + "<lastmod>" + sim.lastmod() + "</lastmod>"
                                + "<changefreq>" + sim.changefreq() + "</changefreq>"
                                + "<priority>" + sim.priority() + "</priority>"
                )
                ;
                writer.write("</url>");
            }
        } catch (IOException exception) {
            throw new NullPointerException("can't create site map");//todo refactor this exception
        }
    }

}
//example siteMapIte
//<url>
//  <loc>http://www.yoursitename.com/</loc>
//  <lastmod>2014-04-20</lastmod>
//  <changefreq>Daily</changefreq>
//  <priority>0.6</priority>
//</url>