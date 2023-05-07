package com.figo.weatherapp.exception;

import com.figo.weatherapp.component.MessageService;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;
import com.figo.weatherapp.payload.ExceptionMessageDTO;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murtozayev Mannguberdi
 * 01.05.2023
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHelper {

    private static final String NOT_FOUND = "_NOT_FOUND";
    private static final String NOT_NULL = "_NOT_NULL";
    private static final String UK_CONSTRAINT = "_UK_CONSTRAINT";

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private boolean isDev() {
        return activeProfile == null || activeProfile.equals("dev") || activeProfile.equals("ser");
    }
    /**
     * The handleException function is a function that handles the exception thrown by the application.
     *
     *
     * @param ex ex Get the list of field errors
     *
     * @return A responseentity&lt;apiresult&lt;errordata&gt;&gt; object
     *
     * @docauthor Manguberdi
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(MethodArgumentNotValidException ex) {

        ex.printStackTrace();
        sendMessageToTelegramChannel(ex);
        List<ErrorData> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> errors.add(new ErrorData(fieldError.getDefaultMessage(), fieldError.getField(), AppConstant.REQUIRED)));
        return new ResponseEntity<>(ApiResult.errorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={RestException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(RestException ex) {
        return new ResponseEntity<>(ApiResult.errorResponse(ex.getUserMsg(), ex.getErrorCode()), ex.getStatus());
    }




    @ExceptionHandler(value = {TypeMismatchException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(TypeMismatchException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(HttpMessageNotReadableException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), AppConstant.CONFLICT),
                HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleExceptionAccessDenied(AccessDeniedException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(MessageService.getMessage("FORBIDDEN_EXCEPTION"), AppConstant.ACCESS_DENIED),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(Exception ex) {
        sendMessageToTelegramChannel(ex);
        log.error("", ex);
        ex.printStackTrace();
        return new ResponseEntity<>(
                ApiResult.errorResponse(
                        ex.toString(),
                        AppConstant.SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AsyncRequestTimeoutException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(AsyncRequestTimeoutException ex) {
        sendMessageToTelegramChannel(ex);
        ex.printStackTrace();
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), 503),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
    /**
     * The sendMessageToTelegramChannel function sends a message to the Telegram channel.(currently not used)
     *
     * @param ex ex Get the exception message
     *
     * @return A void
     *
     * @docauthor Manguberdi Murtozayev
     */
    private void sendMessageToTelegramChannel(Exception ex) {

        log.info("sendMessageToTelegramChannel");

        if (isDev()) {
            return;
        }
        try {
            String message = ex.getMessage();
            ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO();
            exceptionMessageDTO.setMessage(message);
            exceptionMessageDTO.setServiceName("weather-service");
            log.error("sendMessageToTelegramChannel: " + exceptionMessageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
