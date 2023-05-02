package com.figo.weatherapp.aop.executor;

import com.figo.weatherapp.aop.annotation.CheckAuth;
import com.figo.weatherapp.component.MessageService;
import com.figo.weatherapp.enums.PermissionEnum;
import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.utils.AppConstant;
import com.figo.weatherapp.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.figo.weatherapp.utils.CommonUtils.currentRequest;


/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@Slf4j
@Order(value = 1)
@Aspect
@Component
@RequiredArgsConstructor
@CacheConfig(cacheManager = "userCacheManager")
public class CheckAuthAspect {

    @Before(value = "@annotation(checkAuth)")
    public void checkAuthExecutor(CheckAuth checkAuth) {
        check(checkAuth);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        try {
            return httpServletRequest.getHeader(AppConstant.AUTHORIZATION_HEADER);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void check(CheckAuth checkAuth) {

        HttpServletRequest httpServletRequest = currentRequest();

        String token = getTokenFromRequest(httpServletRequest);
        if (token == null)
            throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.BAD_REQUEST);
        //todo get user
        UserDTO currentUser = new UserDTO();

        CommonUtils.setAuthorizeAttr(httpServletRequest, currentUser);

        PermissionEnum[] permission = checkAuth.permission();
        if (permission.length > 0) {
            List<String> notPermissions = CommonUtils.getNotPermissions(currentUser.getPermissions(), permission);
            if (!notPermissions.isEmpty())
                throw RestException.forbidden(notPermissions);
        }
    }



}
