package com.figo.weatherapp.exception;

import com.figo.weatherapp.component.MessageService;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;
import com.figo.weatherapp.payload.ExceptionMessageDTO;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.utils.AppConstant;
import com.figo.weatherapp.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Mo'minov
 * 06.11.2021
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

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        sendMessageToTelegramChannel(ex);
        List<ErrorData> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> errors.add(new ErrorData(fieldError.getDefaultMessage(), fieldError.getField(), AppConstant.REQUIRED)));
        return new ResponseEntity<>(ApiResult.errorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {JpaSystemException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(JpaSystemException ex) {
        ex.printStackTrace();
        sendMessageToTelegramChannel(ex);

        String message = ex.getMessage();

        return new ResponseEntity<>(ApiResult.errorResponse(message, AppConstant.CONFLICT), HttpStatus.CONFLICT);
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

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(MissingServletRequestParameterException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(MissingServletRequestPartException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleExceptionAccessDenied(AccessDeniedException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(MessageService.getMessage("FORBIDDEN_EXCEPTION"), AppConstant.ACCESS_DENIED),
                HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = {MissingPathVariableException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleExceptionNotFound(MissingPathVariableException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(MessageService.getMessage("PATH_NOTFOUND_EXCEPTION"), AppConstant.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(NoHandlerFoundException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }


    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleException(HttpRequestMethodNotSupportedException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse("Method error", 405),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleExceptionHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse("No acceptable", 406),
                HttpStatus.NOT_ACCEPTABLE);
    }


    //METHOD XATO BO'LSA
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ApiResult<ErrorData>> handleExceptionHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        sendMessageToTelegramChannel(ex);
        return new ResponseEntity<>(
                ApiResult.errorResponse(MessageService.getMessage("UNSUPPORTED_MEDIA_TYPE"), 415),
                HttpStatus.METHOD_NOT_ALLOWED);
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
    private void sendMessageToTelegramChannel(Exception ex) {
        log.info("sendMessageToTelegramChannel");

        if (isDev()) {
            return;
        }
        try {
            String message = ex.getMessage();

            UserDTO currentUserOrNull = CommonUtils.getCurrentUserOrNull();

            ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
            String url = servletUriComponentsBuilder.toUriString();

            ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO();

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(currentUserOrNull.getFirstName());
            userDTO.setLastName(currentUserOrNull.getLastName());
            userDTO.setPhoneNumber(currentUserOrNull.getPhoneNumber());
            userDTO.setId(currentUserOrNull.getId());

            exceptionMessageDTO.setUser(userDTO);
            exceptionMessageDTO.setMessage(message);
            exceptionMessageDTO.setUrl(url);
            exceptionMessageDTO.setServiceName("education");
            //todo rabbitMQProducerService.sendExceptions(exceptionMessageDTO);
            //rabbitMQProducerService.sendExceptions(exceptionMessageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
