package com.figo.weatherapp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Constructor;
import java.nio.file.AccessDeniedException;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

@ContextConfiguration(classes = {ExceptionHelper.class})
@ExtendWith(SpringExtension.class)
class ExceptionHelperTest {
    @Autowired
    private ExceptionHelper exceptionHelper;

    /**
     * Method under test: {@link ExceptionHelper#handleException(RestException)}
     */
    @Test
    void testHandleException() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper
                .handleException(RestException.attackResponse());
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertNull(getResult.getErrorCode());
        assertEquals("ATTACK_RESPONSE", getResult.getErrorMsg());
    }

    /**
     * Method under test: {@link ExceptionHelper#handleException(RestException)}
     */
    @Test
    void testHandleException2() {
        RestException ex = mock(RestException.class);
        when(ex.getErrorCode()).thenReturn(-1);
        when(ex.getUserMsg()).thenReturn("User Msg");
        when(ex.getStatus()).thenReturn(HttpStatus.CONTINUE);
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper.handleException(ex);
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CONTINUE, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(-1, getResult.getErrorCode().intValue());
        assertEquals("User Msg", getResult.getErrorMsg());
        verify(ex).getErrorCode();
        verify(ex).getUserMsg();
        verify(ex).getStatus();
    }


    /**
     * Method under test: {@link ExceptionHelper#handleException(Exception)}
     */
    @Test
    void testHandleException4() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper
                .handleException(new Exception("An error occurred"));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(500, getResult.getErrorCode().intValue());
        assertEquals("java.lang.Exception: An error occurred", getResult.getErrorMsg());
    }



    /**
     * Method under test: {@link ExceptionHelper#handleException(TypeMismatchException)}
     */
    @Test
    void testHandleException6() {
        PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent("Source", "Property Name", "Old Value",
                "New Value");

        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper
                .handleException(new TypeMismatchException(propertyChangeEvent, Object.class));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(400, getResult.getErrorCode().intValue());
        assertEquals("Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Object' for"
                + " property 'Property Name'", getResult.getErrorMsg());
    }



    /**
     * Method under test: {@link ExceptionHelper#handleException(TypeMismatchException)}
     */
    @Test
    void testHandleException8() {
        MethodArgumentConversionNotSupportedException ex = mock(MethodArgumentConversionNotSupportedException.class);
        when(ex.getMessage()).thenReturn("An error occurred");
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper.handleException(ex);
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(400, getResult.getErrorCode().intValue());
        assertEquals("An error occurred", getResult.getErrorMsg());
        verify(ex, atLeast(1)).getMessage();
    }


    /**
     * Method under test: {@link ExceptionHelper#handleException(HttpMessageNotReadableException)}
     */
    @Test
    void testHandleException10() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper
                .handleException(new HttpMessageNotReadableException("https://example.org/example"));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(401, getResult.getErrorCode().intValue());
        assertEquals("https://example.org/example", getResult.getErrorMsg());
    }



    /**
     * Method under test: {@link ExceptionHelper#handleException(HttpMessageNotReadableException)}
     */
    @Test
    void testHandleException12() {
        HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);
        when(ex.getMessage()).thenReturn("An error occurred");
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper.handleException(ex);
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(401, getResult.getErrorCode().intValue());
        assertEquals("An error occurred", getResult.getErrorMsg());
        verify(ex, atLeast(1)).getMessage();
    }






    /**
     * Method under test: {@link ExceptionHelper#handleException(AsyncRequestTimeoutException)}
     */
    @Test
    void testHandleException17() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper
                .handleException(new AsyncRequestTimeoutException());
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(503, getResult.getErrorCode().intValue());
        assertNull(getResult.getErrorMsg());
    }



    /**
     * Method under test: {@link ExceptionHelper#handleException(AsyncRequestTimeoutException)}
     */
    @Test
    void testHandleException19() {
        AsyncRequestTimeoutException ex = mock(AsyncRequestTimeoutException.class);
        when(ex.getMessage()).thenReturn("Not all who wander are lost");
        doNothing().when(ex).printStackTrace();
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionResult = exceptionHelper.handleException(ex);
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, actualHandleExceptionResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(503, getResult.getErrorCode().intValue());
        assertEquals("Not all who wander are lost", getResult.getErrorMsg());
        verify(ex, atLeast(1)).getMessage();
        verify(ex).printStackTrace();
    }

    /**
     * Method under test: {@link ExceptionHelper#handleException(AsyncRequestTimeoutException)}
     */
    @Test
    void testHandleException20() {
        AsyncRequestTimeoutException ex = mock(AsyncRequestTimeoutException.class);
        when(ex.getMessage()).thenThrow(new AsyncRequestTimeoutException());
        doThrow(new AsyncRequestTimeoutException()).when(ex).printStackTrace();
        assertThrows(AsyncRequestTimeoutException.class, () -> exceptionHelper.handleException(ex));
        verify(ex).getMessage();
        verify(ex).printStackTrace();
    }

    /**
     * Method under test: {@link ExceptionHelper#handleExceptionAccessDenied(AccessDeniedException)}
     */
    @Test
    void testHandleExceptionAccessDenied() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionAccessDeniedResult = exceptionHelper
                .handleExceptionAccessDenied(new AccessDeniedException("File"));
        assertTrue(actualHandleExceptionAccessDeniedResult.hasBody());
        assertTrue(actualHandleExceptionAccessDeniedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.FORBIDDEN, actualHandleExceptionAccessDeniedResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionAccessDeniedResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(405, getResult.getErrorCode().intValue());
        assertEquals("FORBIDDEN_EXCEPTION", getResult.getErrorMsg());
    }

    /**
     * Method under test: {@link ExceptionHelper#handleExceptionAccessDenied(AccessDeniedException)}
     */
    @Test
    void testHandleExceptionAccessDenied2() {
        ResponseEntity<ApiResult<ErrorData>> actualHandleExceptionAccessDeniedResult = exceptionHelper
                .handleExceptionAccessDenied(null);
        assertTrue(actualHandleExceptionAccessDeniedResult.hasBody());
        assertTrue(actualHandleExceptionAccessDeniedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.FORBIDDEN, actualHandleExceptionAccessDeniedResult.getStatusCode());
        ApiResult<ErrorData> body = actualHandleExceptionAccessDeniedResult.getBody();
        assertFalse(body.getSuccess());
        List<ErrorData> errors = body.getErrors();
        assertEquals(1, errors.size());
        ErrorData getResult = errors.get(0);
        assertEquals(405, getResult.getErrorCode().intValue());
        assertEquals("FORBIDDEN_EXCEPTION", getResult.getErrorMsg());
    }
}

