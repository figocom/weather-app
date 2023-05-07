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
    /**
     * The getMessage function returns the message associated with a given key.

     *
     * @param key key Identify the message to be returned
     *
     * @return The key parameter
     *
     * @docauthor Manguberdi
     */
    public static String getMessage(String key) {
        return key;
    }

    /**
     * The merge function takes in a String action and a String sourceKey.
     * It returns the result of calling the format function on action, passing in sourceKey.toUpperCase() as an argument.

     *
     * @param action action Determine the action to be performed on the sourcekey
     * @param sourceKey sourceKey Specify the key of the source

     *
     * @return A string
     *
     * @docauthor Manguberdi
     */
    private static String merge(String action, String sourceKey) {
        return String.format(action, sourceKey.toUpperCase());
    }


    /**
     * The notFound function takes a String as an argument and returns a String.
     * The returned string is the sourceKey with &quot;_NOT_FOUND&quot; appended to it.
     *
     *
     *
     * @param sourceKey sourceKey Specify the source key of the message
     *
     * @return The merge of the string &quot;s_not_found&quot; and the sourcekey
     *
     * @docauthor Manguberdi
     */
    public static String notFound(String sourceKey) {

        // STUDENT_NOT_FOUND
        return merge("%S_NOT_FOUND", sourceKey);
    }

    /**
     * The alreadyExists function takes in a sourceKey and returns the string
     * &quot;%S_ALREADY_EXISTS&quot; where %S is replaced by the sourceKey.
     *
     *
     *
     * @param sourceKey sourceKey Determine the source of the error
     *
     * @return The string that is the result of calling the merge function with two arguments, &quot;%s_already_exists&quot; and sourcekey
     *
     * @docauthor Manguberdi
     */
    public static String alreadyExists(String sourceKey) {
        return merge("%S_ALREADY_EXISTS", sourceKey);
    }

    /*===================OTHER SERVICES===================*/

    /**
     * The otherServiceError function takes a String as an argument and returns a String.
     * The function is used to create error messages for when the user tries to use
     * services that are not available in the current version of this program.

     *
     * @param serviceName serviceName Pass the name of the service that is being used
     *
     * @return The string error_in_$s with the servicename variable replacing $s
     *
     * @docauthor Manguberdi
     */
    public static String otherServiceError(String serviceName) {

        return merge("ERROR_IN_$S", serviceName);
    }
}
