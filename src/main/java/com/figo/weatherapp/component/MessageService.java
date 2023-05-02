package com.figo.weatherapp.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@Component
@RequiredArgsConstructor
public class MessageService {
    public static String getMessage(String key) {
        return key;
    }

    private static String merge(String action, String sourceKey) {
        return String.format(action, sourceKey.toUpperCase());
    }

    /*==================SUCCESS===================*/

    public static String successEdit(String sourceKey) {
        return merge("%S_SUCCESSFULLY_EDITED", sourceKey);
    }

    public static String successSave(String sourceKey) {
        return merge("%S_SUCCESSFULLY_SAVED", sourceKey);
    }

    public static String successDelete(String sourceKey) {
        return merge("%S_SUCCESSFULLY_DELETED", sourceKey);
    }


    /*===================ERROR===================*/

    public static String cannotDelete(String sourceKey) {
        // STUDENT_CANNOT_BE_DELETED
        return merge("%S_CANNOT_BE_DELETED", sourceKey);
    }

    public static String notFound(String sourceKey) {
        // STUDENT_NOT_FOUND
        return merge("%S_NOT_FOUND", sourceKey);
    }

    public static String alreadyExists(String sourceKey) {
        // STUDENT_ALREADY_EXISTS
        return merge("%S_ALREADY_EXISTS", sourceKey);
    }

    /*===================OTHER SERVICES===================*/

    public static String otherServiceError(String serviceName) {
        // ERROR_IN_ACADEMIC_SERVICE
        return merge("ERROR_IN_$S", serviceName);
    }
}
