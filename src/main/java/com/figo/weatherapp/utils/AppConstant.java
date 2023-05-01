package com.figo.weatherapp.utils;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
public interface AppConstant {
    String GLOBAL_PHONE_NUMBER_REGEX = "^\\+998[0-9]{9}$";
    String GLOBAL_UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
    String DOMAIN = "http://10.10.10.226:8080";
    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1";



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


    String DEFAULT_COLOR = "#000000";
    String APP_REF_OUT_TRANSFER_COLOR = "red";
    String APP_REF_IN_TRANSFER_COLOR = "#45B26B";

    String TABLE_NAME = "tableName";
    String ASIA_TASHKENT_TIME_ZONE = "Asia/Tashkent";
    DateTimeFormatter DATE_FORMAT_DATE_AND_HOUR = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String SERVICE_PASSWORD_HEADER = "";
    String SERVICE_USERNAME_HEADER = "";
}
