package com.figo.weatherapp.utils;

import com.figo.weatherapp.controller.admin.AdminController;
import com.figo.weatherapp.controller.auth.AuthController;
import com.figo.weatherapp.controller.city.CityController;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
public interface AppConstant {
    String GLOBAL_UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1/";
    String AUTHORIZATION_HEADER = "Authorization";
    String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    String REQUEST_ATTRIBUTE_CURRENT_USER = "User";
    String REQUEST_ATTRIBUTE_CURRENT_USER_ID = "UserId";
    String REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS = "Permissions";
    Integer NO_ITEMS_FOUND = 300032;
    Integer SERVER_ERROR = 500;
    Integer REQUIRED = 400;
    Integer CONFLICT = 401;
    Integer NOT_FOUND = 404;
    Integer ACCESS_DENIED = 405;
    String SERVICE_PASSWORD_HEADER = "Password";
    String SERVICE_USERNAME_HEADER = "username";
    String[] OPEN_PAGES_FOR_ALL_METHOD = {
         AuthController.AUTH_CONTROLLER_PATH + "**",
            "/ui",
            "/api-docs/**",
            "/swagger-ui/**",
            "/webjars/**",
    };
    String[] OPEN_PAGES_FOR_ADMIN = {
            AdminController.AdminControllerPath + "**",
            CityController.CITY_CONTROLLER_PATH + "**",
    };

}
