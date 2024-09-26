package ir.technyx.icm_core.security.filter;

import ir.technyx.icm_core.domain.company.menu.MenuAccessLevel;
import ir.technyx.icm_core.dto.ResUserAuthenticationDto;
import ir.technyx.icm_core.repository.company.menu.MenuAccessLevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

@Component
@AllArgsConstructor
public class IcmPermissionEvaluator implements PermissionEvaluator {

    private final MenuAccessLevelRepository menuAccessLevelRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
        if (authentication != null && authentication.isAuthenticated() && accessType instanceof String a) {
            if (a.equals("hasAccess")) {
                if (permission instanceof String p) {
                    return validateAccess(authentication, p);
                }
            }

        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    private boolean validateAccess(Authentication authentication, String permission) {
        String currentUrl ="";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {


            if(HttpMethod.GET.toString().equals(servletRequestAttributes.getRequest().getMethod())){
                HashMap<String,String> pathVariables = (HashMap<String, String>) servletRequestAttributes.getRequest().getAttribute("org.springframework.web.servlet.View.pathVariables");
                if(pathVariables != null && !pathVariables.isEmpty()){
                    currentUrl = findBestMatchingGetMappingRequest(servletRequestAttributes);
                }else {
                    currentUrl = servletRequestAttributes.getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
                }
            }else {
                currentUrl = servletRequestAttributes.getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
            }

            Optional<MenuAccessLevel> optionalMenuAccessLevel = menuAccessLevelRepository
                    .getMenuAccessLevelByUserIdAndMenuUrl(((ResUserAuthenticationDto) authentication.getPrincipal())
                            .getId(), currentUrl);
            return optionalMenuAccessLevel
                    .map(menuAccessLevel -> menuAccessLevel.getPermissions().contains(permission))
                    .orElse(false);
        }
        return false;
    }

    private String findBestMatchingGetMappingRequest(ServletRequestAttributes servletRequestAttributes){
        String bestMatchingPattern = servletRequestAttributes.getRequest()
                .getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        int firstIndexPathVariableIndex = bestMatchingPattern.indexOf("/{");
        return bestMatchingPattern.substring(0, firstIndexPathVariableIndex);
    }
}
