package com.figo.weatherapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@AllArgsConstructor
@Getter
@Slf4j
public enum PermissionEnum {
    GET_ROLE(false),
    ADD_ROLE( false),
    EDIT_ROLE( false),
    DELETE_ROLE( false),
    GET_LANGUAGES(false),
    GET_KEYS( false);
    private boolean deleted;
}
