package ir.technyx.icm_core.service.common;

import ir.technyx.icm_core.domain.common.LocationInfo;
import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.common.SystemProperties;
import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.company.menu.Menu;
import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import ir.technyx.icm_core.domain.company.page.Gallery;
import ir.technyx.icm_core.domain.company.page.faq.Faq;
import ir.technyx.icm_core.domain.company.page.news.News;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.domain.user.UserAccess;
import ir.technyx.icm_core.repository.common.LocationInfoRepository;
import ir.technyx.icm_core.repository.common.ManagementTypeRepository;
import ir.technyx.icm_core.repository.common.SystemPropertiesRepository;
import ir.technyx.icm_core.repository.company.CompanyRepository;
import ir.technyx.icm_core.repository.company.CompanyUserRepository;
import ir.technyx.icm_core.repository.company.menu.MenuRepository;
import ir.technyx.icm_core.repository.user.RoleRepository;
import ir.technyx.icm_core.repository.user.UserAccessRepository;
import ir.technyx.icm_core.repository.user.UserRepository;
import ir.technyx.icm_core.security.conf.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Deprecated
//TODO @nader check and refactor this class
public class BootstrapServiceImpl implements BootstrapService {

    private final UserRepository userRepository;
    private final UserAccessRepository userAccessRepository;
    private final RoleRepository roleRepository;
    private final ManagementTypeRepository managementTypeRepository;
    private final MenuRepository menuRepository;
    private final CompanyRepository companyRepository;
    private final CompanyUserRepository companyUserRepository;
    private final LocationInfoRepository locationInfoRepository;
    private final SystemPropertiesRepository systemPropertiesRepository;
    //private final RsaEncryptionService rsaEncryptionService;


    @Override
    public void boot() throws Exception {
        if (!managementTypeRepository.exists(Example.of(
                new ManagementType(ManagementType.GENDER, ManagementType.GENDER_MAN, ManagementType.GENDER_MAN, 1)))) {
            insertRoles();
            insertDefaultUser();
            insertDefaultCompany();
            insertDefaultCompanyUserList();
            insertAllTypes();
            insertDefaultMenusForIcmCompany();
            assignDefaultRoleToDefaultUser();
            insertSystemProperties();
            insertCountry();
            insertIranCountryProvince();
            //rsaKeyGenerator();
        }
    }

    private void insertRoles() {
        List<Role> roles = List.of(
                new Role(Role.ROLE_ICM_ADMIN),
                new Role(Role.ROLE_ICM_USER),
                new Role(Role.ROLE_CU_ADMIN),
                new Role(Role.ROLE_CU_MEMBER)
        );
        roleRepository.saveAllAndFlush(roles);
    }

    private void insertDefaultUser() {
        User user = new User();
        user.setFirstName("icmAdmin");
        user.setLastName("icmAdmin");
        user.setUsername("icmAdmin");
        user.setPassword(ApplicationConfig.passwordEncoder().encode("1234"));
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        userRepository.saveAndFlush(user);
    }

    private void insertDefaultCompany() {
        Company icmCompany = new Company();
        icmCompany.setName(Company.ICM_COMPANY_NAME);
        icmCompany.setCode(Company.ICM_COMPANY_CODE);
        icmCompany.setNationalCode("48759655");
        icmCompany.setPhone("09359974976");
        icmCompany.setHostUrl("http://www.icm.com");
        icmCompany.setRegistrationInfo(getBootRegistrationInfo());
        companyRepository.saveAndFlush(icmCompany);
    }

    private void insertDefaultCompanyUserList() {
        final Company icmCompany = companyRepository.findByCode(Company.ICM_COMPANY_CODE).orElseThrow(NullPointerException::new);
        final User user = getDefaultUser();
        CompanyUser companyUser = new CompanyUser(icmCompany, user, getBootRegistrationInfo());
        companyUserRepository.saveAndFlush(companyUser);
    }

    private RegistrationInfo getBootRegistrationInfo() {
        RegistrationInfo registrationInfo = new RegistrationInfo();
        registrationInfo.setRegisterUserId(getDefaultUser().getId());
        registrationInfo.setRegisterDate(LocalDateTime.now());
        return registrationInfo;
    }

    private User getDefaultUser() {
        return userRepository.findByUsername("icmAdmin").orElseThrow(NullPointerException::new);
    }

    private void insertAllTypes() {
        insertGenderTypes();
        insertAccessType();
        insertLocationType();
        insertPageTypes();
        insertContentTypes();
        insertFileTypes();
    }

    private void insertGenderTypes() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType(ManagementType.GENDER, ManagementType.GENDER_MAN, ManagementType.GENDER_MAN, 1),
                new ManagementType(ManagementType.GENDER, ManagementType.GENDER_WOMAN, ManagementType.GENDER_WOMAN, 2),
                new ManagementType(ManagementType.GENDER, ManagementType.GENDER_OTHER, ManagementType.GENDER_OTHER, 3));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }

    private void insertAccessType() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType(ManagementType.ACCESS_TYPE, ManagementType.ACCESS_LEVEL_WRITE, ManagementType.ACCESS_LEVEL_WRITE, 1),
                new ManagementType(ManagementType.ACCESS_TYPE, ManagementType.ACCESS_LEVEL_READ, ManagementType.ACCESS_LEVEL_READ, 2),
                new ManagementType(ManagementType.ACCESS_TYPE, ManagementType.ACCESS_LEVEL_UPDATE, ManagementType.ACCESS_LEVEL_UPDATE, 3),
                new ManagementType(ManagementType.ACCESS_TYPE, ManagementType.ACCESS_LEVEL_DELETE, ManagementType.ACCESS_LEVEL_DELETE, 4));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }

    private void insertLocationType() {
        List<ManagementType> locationTypes = List.of(
                new ManagementType(ManagementType.LOCATION_TYPE, ManagementType.LOCATION_COUNTRY, ManagementType.LOCATION_COUNTRY, 1),
                new ManagementType(ManagementType.LOCATION_TYPE, ManagementType.LOCATION_PROVINCE, ManagementType.LOCATION_PROVINCE, 2),
                new ManagementType(ManagementType.LOCATION_TYPE, ManagementType.LOCATION_CITY, ManagementType.LOCATION_CITY, 3));
        managementTypeRepository.saveAllAndFlush(locationTypes);
    }

    private void insertPageTypes() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType(ManagementType.SPECIALIZED_PAGE_TYPE, ManagementType.SPECIALIZED_PAGE_FAQ_TYPE, ManagementType.SPECIALIZED_PAGE_FAQ_TYPE, 1),
                new ManagementType(ManagementType.SPECIALIZED_PAGE_TYPE, ManagementType.SPECIALIZED_PAGE_TYPE_NEWS, ManagementType.SPECIALIZED_PAGE_TYPE_NEWS, 2),
                new ManagementType(ManagementType.SPECIALIZED_PAGE_TYPE, ManagementType.SPECIALIZED_PAGE_TYPE_GALLERY, ManagementType.SPECIALIZED_PAGE_TYPE_GALLERY, 3),
                new ManagementType(ManagementType.SPECIALIZED_PAGE_TYPE, ManagementType.SPECIALIZED_PAGE_TYPE_CONTACT_US, ManagementType.SPECIALIZED_PAGE_TYPE_CONTACT_US, 4),
                new ManagementType(ManagementType.CLIENT_PAGE_TYPE, ManagementType.CLIENT_PAGE_TYPE_TUTORIAL, ManagementType.CLIENT_PAGE_TYPE_TUTORIAL, 5),
                new ManagementType(ManagementType.CLIENT_PAGE_TYPE, ManagementType.CLIENT_PAGE_TYPE_BLOG, ManagementType.CLIENT_PAGE_TYPE_BLOG, 6));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }

    private void insertContentTypes() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType(ManagementType.CONTENT_TYPE, ManagementType.CONTENT_TYPE_TEXT, ManagementType.CONTENT_TYPE_TEXT, 1),
                new ManagementType(ManagementType.CONTENT_TYPE, ManagementType.CONTENT_TYPE_IMAGE, ManagementType.CONTENT_TYPE_IMAGE, 2),
                new ManagementType(ManagementType.CONTENT_TYPE, ManagementType.CONTENT_TYPE_TEXT_IMAGE, ManagementType.CONTENT_TYPE_TEXT_IMAGE, 3));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }

    private void insertFileTypes() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType(ManagementType.FILE_TYPE, ManagementType.FILE_TYPE_JPG, ManagementType.FILE_TYPE_JPG, 1),
                new ManagementType(ManagementType.FILE_TYPE, ManagementType.FILE_TYPE_PNG, ManagementType.FILE_TYPE_PNG, 2),
                new ManagementType(ManagementType.FILE_TYPE, ManagementType.FILE_TYPE_GIF, ManagementType.FILE_TYPE_GIF, 3));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }


    private void insertDefaultMenusForIcmCompany() {
        final Company icmCompany = companyRepository.findByCode(Company.ICM_COMPANY_CODE).orElseThrow(NullPointerException::new);
        final RegistrationInfo registrationInfo = getBootRegistrationInfo();
        final List<Menu> menus = new ArrayList<>();
        getRoleIcmAdminMenu().forEach((key, value) -> menus.add(createMenu(key, value, icmCompany, registrationInfo)));
        menuRepository.saveAllAndFlush(menus);
    }

    private Menu createMenu(String url, String iconPath, Company company, RegistrationInfo registrationInfo) {
        Menu menu = new Menu();
        menu.setUrl(url);
        menu.setIconPath(iconPath);
        menu.setActive(true);
        menu.setRegistrationInfo(registrationInfo);
        menu.setCompany(company);
        return menu;
    }

    private void assignDefaultRoleToDefaultUser() {
        List<Menu> menus = menuRepository.getAllMenuByCompanyCode(Company.ICM_COMPANY_CODE).orElse(new ArrayList<>());
        Role role = roleRepository.findByName(Role.ROLE_ICM_ADMIN).orElseThrow(NullPointerException::new);

        UserAccess userAccess = new UserAccess();
        userAccess.setRole(role);
        userAccess.setUser(getDefaultUser());

        List<MenuAccessLevel> menuAccessLevels = new ArrayList<>();


        menus.forEach(m -> {
            MenuAccessLevel menuAccessLevel = new MenuAccessLevel();
            menuAccessLevel.setMenu(m);
            menuAccessLevel.setWrite(true);
            menuAccessLevel.setRead(true);
            menuAccessLevel.setUpdate(true);
            menuAccessLevel.setDelete(true);
            menuAccessLevel.setUserAccess(userAccess);
            menuAccessLevels.add(menuAccessLevel);

        });

        userAccess.setMenuAccessLevelList(menuAccessLevels);
        userAccessRepository.saveAndFlush(userAccess);

    }

    private Map<String, String> getRoleIcmAdminMenu() {
        Map<String, String> menusMap = new HashMap<>();
        menusMap.put("/icm/user/login", "");
        menusMap.put("/icm/user/logout", "");
        menusMap.put("/icm/baseInfo/v1/getIcmRoles", "");
        menusMap.put("/icm/baseInfo/v1/getRoles", "");
        menusMap.put("/icm/baseInfo/v1/getIcmMenuAccessLevelList", "");
        menusMap.put("/icm/baseInfo/v1/getMenuAccessLevelListByCompanyId", "");
        menusMap.put("/icm/baseInfo/v1/getIcmMenuAccessLevelListByUserId", "");
        menusMap.put("/icm/baseInfo/v1/getMenuAccessLevelListByUserId", "");
        menusMap.put("/icm/baseInfo/v1/getTypesByCode", "");
        menusMap.put("/icm/baseInfo/v1/getStructuredAllType", "");
        menusMap.put("/icm/baseInfo/v1/getLocationTypes", "");
        menusMap.put("/icm/baseInfo/v1/getGenderTypes", "");
        menusMap.put("/icm/baseInfo/v1/getAccessLevelTypes", "");
        menusMap.put("/icm/baseInfo/v1/registerManagementType", "");
        menusMap.put("/icm/baseInfo/v1/registerManagementTypes", "");
        menusMap.put("/icm/baseInfo/v1/updateManagementType", "");
        menusMap.put("/icm/baseInfo/v1/updateManagementTypes", "");
        menusMap.put("/icm/baseInfo/v1/deleteManagementType", "");
        menusMap.put("/icm/baseInfo/v1/deleteAllManagementTypes", "");
        menusMap.put("/icm/user/v1/registerIcmUser", "");
        menusMap.put("/icm/user/v1/updateIcmUser", "");
        menusMap.put("/icm/user/v1/deleteIcmUser", "");
        menusMap.put("/icm/user/v1/getIcmUser", "");
        menusMap.put("/icm/user/v1/getAllIcmUser", "");
        menusMap.put("/icm/user/v1/registerUser", "");
        menusMap.put("/icm/user/v1/updateUser", "");
        menusMap.put("/icm/user/v1/delete", "");
        menusMap.put("/icm/user/v1/getUser", "");
        menusMap.put("/icm/user/v1/getAllUserByCompany", "");
        menusMap.put("/icm/accessManagement/v1/setIcmUserRoles", "");
        menusMap.put("/icm/accessManagement/v1/deleteIcmUserRoles", "");
        menusMap.put("/icm/accessManagement/v1/setUserRoles", "");
        menusMap.put("/icm/accessManagement/v1/deleteAllUserRoles", "");
        menusMap.put("/icm/accessManagement/v1/setIcmAccessLevels", "");
        menusMap.put("/icm/accessManagement/v1/deleteAllIcmAccessLevels", "");
        menusMap.put("/icm/accessManagement/v1/setAccessLevels", "");
        menusMap.put("/icm/accessManagement/v1/deleteAllAccessLevels", "");
        //todo remove this code;
        menusMap.put("/icm/company/v1/saveCompany", "");
        menusMap.put("/icm/page/v1/addPage", "");
        menusMap.put("/icm/page/v1/updatePage", "");
        menusMap.put("/icm/page/v1/getPage", "");
        menusMap.put("/icm/page/v1/getPageByCode", "");
        menusMap.put("/icm/page/v1/getAllPageByCompanyCode", "");
        menusMap.put("/icm/page/v1/deletePageById/{id}", "");
        menusMap.put("/icm/page/v1/deleteAllPageByCompanyCode", "");
        menusMap.put("/icm/page/v1/addPageContent", "");
        menusMap.put("/icm/page/v1/updatePageContent", "");
        menusMap.put("/icm/page/v1/getPageContentById", "");
        menusMap.put("/icm/page/v1/getPageContentByPageId", "");
        menusMap.put("/icm/page/v1/getPageContentByPageCode", "");
        menusMap.put("/icm/page/v1/deletePageContentById", "");
        menusMap.put("/icm/page/v1/deleteAllPageContentByPageId", "");
        menusMap.put("/icm/page/v1/deleteAllPageContentByPageCode", "");
        return menusMap;
    }

    private void insertSystemProperties() {
        List<SystemProperties> systemProperties = new ArrayList<>();
        systemProperties.add(new SystemProperties(SystemProperties.SUPPORT_PAGE, Faq.FAQ_PAGE_TYPE, getBootRegistrationInfo()));
        systemProperties.add(new SystemProperties(SystemProperties.SUPPORT_PAGE, News.NEWS_PAGE_TYPE, getBootRegistrationInfo()));
        systemProperties.add(new SystemProperties(SystemProperties.SUPPORT_PAGE, Gallery.GALLERY_PAGE_TYPE, getBootRegistrationInfo()));
        systemPropertiesRepository.saveAllAndFlush(systemProperties);
    }

    private void insertCountry() {
        ManagementType countryType = managementTypeRepository.findByCodeAndSubType(ManagementType.LOCATION_TYPE, ManagementType.LOCATION_COUNTRY).orElseThrow(NullPointerException::new);
        LocationInfo iranCountry = new LocationInfo(null, countryType, "Iran", "098");
        locationInfoRepository.saveAndFlush(iranCountry);
    }

    private void insertIranCountryProvince() {
        LocationInfo parentLocationInfo = getDefaultLocation();
        ManagementType provinceType = managementTypeRepository.findByCodeAndSubType(ManagementType.LOCATION_TYPE, ManagementType.LOCATION_PROVINCE).orElseThrow(NullPointerException::new);
        List<LocationInfo> locationInfoList = new ArrayList<>();
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "ardabil", "045"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "isfahan", "031"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "alborz", "026"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "ilam", "084"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "east_azerbaijan", "041"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "west_azerbaijan", "044"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "bushehr", "077"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "tehran", "021"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "chaharmahal_va_bakhtiari", "038"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "south_khorasan", "051"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "khorasan_razavi", "056"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "north_khorasan", "058"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "khuzestan", "061"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "zanjan", "024"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "semnan", "023"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "sistan_and_baluchestan", "054"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "fars", "071"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "qazvin", "028"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "qom", "025"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "kurdistan", "087"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "kerman", "034"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "kermanshah", "083"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "kohgiluyeh_and_boyer_ahmad", "074"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "golestan", "017"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "gilan", "013"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "lorestan", "066"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "mazandaran", "011"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "central", "086"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "hormozgan", "076"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "hamadan", "081"));
        locationInfoList.add(new LocationInfo(parentLocationInfo, provinceType, "yazd", "035"));

        locationInfoRepository.saveAllAndFlush(locationInfoList);
    }

    private LocationInfo getDefaultLocation() {
        LocationInfo iranLocationInfo = locationInfoRepository.findByTitle("Iran").orElseThrow(NullPointerException::new);
        locationInfoRepository.flush();
        return iranLocationInfo;
    }


  /*  private void insertInfoTypes() {
        List<ManagementType> managementTypes = List.of(
                new ManagementType("Info", "Warning", "Warning", 1),
                new ManagementType("Info", "Content", "Content", 2),
                new ManagementType("Info", "About", "About", 3));
        managementTypeRepository.saveAllAndFlush(managementTypes);
    }
*/

   /* private void rsaKeyGenerator() throws Exception {


        KeyPair keyPair = rsaEncryptionService.generateKeyPair();

        byte[] encryptedText = rsaEncryptionService.encryptText("nader Aria is batman", keyPair.getPublic());

        System.out.println("encrypted text : " + Base64.getEncoder().encodeToString(encryptedText));


        String decryptText = rsaEncryptionService.decryptText(encryptedText, keyPair.getPrivate());

        System.out.println("decrypt Text : " + decryptText);

        rsaEncryptionService.saveKeysToFile(keyPair, "rsa_key", System.getProperty("user.home") + File.separator + "Desktop");

        rsaEncryptionService.saveKeysToDatabase(keyPair, 6);

        rsaEncryptionService.loadKeysFromDatabase(6, 1);
    }*/

}
