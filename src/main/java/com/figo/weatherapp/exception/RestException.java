package com.figo.weatherapp.exception;


import com.figo.weatherapp.component.MessageService;
import com.figo.weatherapp.net.ErrorData;
import com.figo.weatherapp.utils.AppConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private String userMsg;
    private HttpStatus status;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private List<ErrorData> errors;
    private Integer errorCode;

    private RestException(String resourceName, String fieldName, Object fieldValue, String userMsg) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.userMsg = userMsg;
        this.status = HttpStatus.BAD_REQUEST;
        this.errorCode = AppConstant.NO_ITEMS_FOUND;
    }

    private RestException(String resourceName, String fieldName, Object fieldValue, String userMsg, HttpStatus status) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(String userMsg, HttpStatus status) {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(String userMsg, int errorCode, HttpStatus status) {
        super(userMsg);
        this.errors = Collections.singletonList(new ErrorData(userMsg, errorCode));
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(HttpStatus status, List<ErrorData> errors) {
        this.status = status;
        this.errors = errors;
    }

    /**
     * The restThrow function is a helper function that allows us to throw an exception with a custom message and HTTP status code.
     *
     *
     * @param userMsg userMsg Set the message of the restexception object
     * @param httpStatus httpStatus Set the http status code of the response
     *
     * @return A restexception object
     *
     * @docauthor Manguberdi
     */
    public static RestException restThrow(String userMsg, HttpStatus httpStatus) {
        return new RestException(userMsg, httpStatus);
    }

    /**
     * The restThrow function is a helper function that creates and throws a RestException.
     *
     *
     *
     * @param errors&lt;ErrorData&gt; errors Pass in a list of errordata objects to the restexception constructor
     * @param status status Set the status code of the response
     *
     * @return A restexception object with the status and errors passed in
     *
     * @docauthor Manguberdi
     */
    public static RestException restThrow(List<ErrorData> errors, HttpStatus status) {

        return new RestException(status, errors);
    }

    public static RestException restThrow(String message) {
        return new RestException(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * The notFound function returns a RestException with the message
     * &quot;The resource [resourceKey] was not found.&quot; and an HTTP status of 404.
     *
     *
     * @param resourceKey resourceKey Get the message from the messageservice class
     *
     * @return A new restexception object with a message and an httpstatus of not_found
     *
     * @docauthor Manguberdi
     */
    public static RestException notFound(String resourceKey) {

        return new RestException(
                MessageService.notFound(resourceKey),
                HttpStatus.NOT_FOUND
        );
    }

    public static RestException otherServiceError(String serviceName) {
        return new RestException(
                MessageService.otherServiceError(serviceName),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * The badRequest function is a helper function that returns a RestException with the given resourceKey as its message and an HttpStatus of BAD_REQUEST.
     *
     *
     * @param resourceKey resourceKey Get the message from the resource bundle
     *
     * @return A new restexception with a message and status code
     *
     * @docauthor Manguberdi
     */
    public static RestException badRequest(String resourceKey) {

        return new RestException(
                MessageService.getMessage(resourceKey),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * The alreadyExists function is a helper function that returns an exception with the message
     * &quot;The resource already exists&quot; and a status code of 409.
     *
     *
     * @param resourceKey resourceKey Get the message from the messageservice class
     *
     * @return An instance of the restexception class
     *
     * @docauthor Manguberdi
     */
    public static RestException alreadyExists(String resourceKey) {

        return new RestException(
                MessageService.alreadyExists(resourceKey),
                HttpStatus.CONFLICT
        );
    }

    public static RestException attackResponse() {
        return new RestException(
                MessageService.getMessage("ATTACK_RESPONSE"),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * The forbidden function returns a RestException with the message &quot;FORBIDDEN&quot; and an HttpStatus of BAD_REQUEST.

     * @return A restexception with a message of &quot;forbidden&quot; and an httpstatus of bad_request
     *
     * @docauthor Manguberdi
     */
    public static RestException forbidden() {

        return new RestException(
                MessageService.getMessage("FORBIDDEN"),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * The forbidden function is a helper function that returns an instance of the RestException class.
     * The purpose of this function is to return a 403 Forbidden error when the user does not have permission to access something.
     *
     *
     * @param msg&lt;String&gt; msg Display the message in the exception
     *
     * @return A restexception object with the message &quot;forbidden
     *
     * @docauthor Manguberdi
     */
    public static RestException forbidden(List<String> msg) {

        return new RestException(
                "Forbidden. You don't have permissions : " + msg,
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * The internalServerError function returns a RestException with the INTERNAL_SERVER_ERROR message and HttpStatus.INTERNAL_SERVER_ERROR status code.
     *
     *
     *
     * @return A restexception object with the message &quot;internal_server_error&quot; and a status code of 500
     *
     * @docauthor Manguberdi
     */
    public static RestException internalServerError() {

        return new RestException(
                MessageService.getMessage("INTERNAL_SERVER_ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
