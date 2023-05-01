package com.figo.weatherapp.utils;

import com.figo.weatherapp.enums.PermissionEnum;
import com.figo.weatherapp.payload.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.figo.weatherapp.utils.AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER;
import static com.figo.weatherapp.utils.AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_ID;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@Component
public class CommonUtils {

    public static String ATTACHMENT_DOWNLOAD_PATH;
    public static String DOMAIN;

    public static String urlBuilder() {
        return DOMAIN + "/api/" + ATTACHMENT_DOWNLOAD_PATH;
    }

    //QAVS LARNI QIRQIB TASHLAYDI
    public static String replaceBracket(String object) {

        String s = object.replaceAll("[\\[\\]\" ]", "");
        System.out.println(s);
        return s;
    }
    public static UserDTO getUserDTOFromRequestForAuditing() {
        try {
            HttpServletRequest httpServletRequest = currentRequest();
            return (UserDTO) httpServletRequest.getAttribute(REQUEST_ATTRIBUTE_CURRENT_USER);
        } catch (Exception e) {
            return null;
        }
    }
    public static String getUserIdFromRequest(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(REQUEST_ATTRIBUTE_CURRENT_USER_ID);
    }

    public static String getUserPermissionsFromRequest(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS);
    }

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }

    //REQUEST DAN TOKENNI OLIB BERADI
    public static String getTokenFromRequest() {
        HttpServletRequest httpServletRequest = currentRequest();
        if (Objects.isNull(httpServletRequest)) {
            return "";
        }
        String header = httpServletRequest.getHeader(AppConstant.AUTHORIZATION_HEADER);
        return Objects.nonNull(header) ? header : (String) httpServletRequest.getAttribute(AppConstant.AUTHORIZATION_HEADER);
    }

    public static void setAuthorizeAttr(HttpServletRequest httpServletRequest, UserDTO currentUser) {
        httpServletRequest.setAttribute(REQUEST_ATTRIBUTE_CURRENT_USER_ID, currentUser.getId());
        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS, currentUser.getPermissions());
        httpServletRequest.setAttribute(REQUEST_ATTRIBUTE_CURRENT_USER, currentUser);
    }

    public static List<String> getNotPermissions(List<String> permissions, PermissionEnum[] permission) {
        List<String> notPermissions = new ArrayList<>();
        if (permissions == null)
            permissions = new ArrayList<>();
        for (PermissionEnum permissionEnum : permission) {
            if (!permissions.contains(permissionEnum.name()))
                notPermissions.add(permissionEnum.name());
        }
        return notPermissions;
    }
    public static UserDTO getCurrentUserOrNull() {
        try {
            HttpServletRequest httpServletRequest = currentRequest();
            UserDTO currentUser = (UserDTO) httpServletRequest.getAttribute(REQUEST_ATTRIBUTE_CURRENT_USER);
            if (currentUser == null || Objects.isNull(currentUser.getId())) {
                return new UserDTO();
            }
            return currentUser;
        } catch (Exception e) {
            return new UserDTO();
        }
    }
}
