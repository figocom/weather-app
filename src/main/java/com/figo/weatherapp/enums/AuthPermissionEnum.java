package com.figo.weatherapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

//FAQAT USHBU MICROSERVICE UCHUN ISHLATILADIGAN PERMISSIONLAR
@Getter
@AllArgsConstructor
public enum AuthPermissionEnum {
    GET_ROLE(false),
    ADD_ROLE( false),
    EDIT_ROLE( false),
    DELETE_ROLE( false),
    GET_LANGUAGES(false),
    GET_KEYS( false);

    private boolean deleted;

}