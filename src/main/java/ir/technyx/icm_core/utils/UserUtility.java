package ir.technyx.icm_core.utils;

import ir.technyx.icm_core.domain.common.RegistrationInfo;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.ResUserAuthenticationDto;
import ir.technyx.icm_core.security.conf.ApplicationConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Random;

public class UserUtility {


    @Value("${random_code_number}")
    private static int random_code_number;

    @Value("${random_ticket_number}")
    private static int random_ticket_number;

    public static String createCompanyCode(String hostUrl) {
        String hostName = getHostName(hostUrl);
        if (StringUtils.isNotBlank(hostName)) {
            return hostName.concat(String.valueOf(new Random().nextInt(random_code_number)));
        }
        throw new NullPointerException("company.hostUrlIsWrong");
    }

    public static String getHostName(String url) {
        try {
            URI uri = new URI(url);
            String hostname = uri.getHost();

            if (StringUtils.isNotBlank(hostname)) {
                return hostname.startsWith("www.") ? hostname.substring(4, 9) : hostname.substring(0, 5);
            }

            return hostname;
        } catch (URISyntaxException e) {
           return null;
        }
    }

    public static String getRandomUsername() {
        return RandomStringUtils.randomAlphabetic(5, 10);
    }

    public static String getRandomUsername(String companyCode) {
        return companyCode.concat("_" + getRandomUsername());
    }

    public static String getRandomUsername(String companyCode, String serial) {
        return getRandomUsername(companyCode).concat("_").concat(serial);
    }

    public static String encodePassword(String password) {
        return ApplicationConfig.passwordEncoder().encode(password);
    }

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Long getCurrentUserId() {
        return ((ResUserAuthenticationDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static boolean haveIcmAdminRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(Role.ROLE_ICM_ADMIN));
    }

    public static boolean haveIcmUserRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals(Role.ROLE_ICM_USER));
    }

    public static boolean haveIcmRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> (a.getAuthority().equals(Role.ROLE_ICM_ADMIN) ||
                                a.getAuthority().equals(Role.ROLE_ICM_USER)));
    }

    public static boolean haveCuAdminRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(Role.ROLE_CU_ADMIN));
    }

    public static boolean haveCuMemberRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(Role.ROLE_CU_MEMBER));
    }

    public static RegistrationInfo getCurrentRegistrationInfo() {
        return new RegistrationInfo(getCurrentUserId(), LocalDateTime.now());
    }

    public static String createTicketCode(String companyCode, String ticketTypeTitle) {

        if (StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(ticketTypeTitle)) {
            return ticketTypeTitle.concat("_").concat(companyCode)
                    .concat(String.valueOf(new Random().nextInt(random_ticket_number)));
        }
        throw new NullPointerException("company.codeOrTicketTypeShouldNotBeEmpty");
    }


}
