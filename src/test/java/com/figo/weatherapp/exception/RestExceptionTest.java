package com.figo.weatherapp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class RestExceptionTest {
    /**
     * Method under test: {@link RestException#restThrow(String)}
     */
    @Test
    void testRestThrow() {
        RestException actualRestThrowResult = RestException.restThrow("An error occurred");
        assertEquals("An error occurred", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow2() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.CONTINUE);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.CONTINUE, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow3() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.SWITCHING_PROTOCOLS);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.SWITCHING_PROTOCOLS, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow4() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.PROCESSING);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.PROCESSING, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow5() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.CHECKPOINT);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.CHECKPOINT, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow6() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.OK);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.OK, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow7() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.CREATED);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.CREATED, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow8() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.ACCEPTED);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.ACCEPTED, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow9() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg",
                HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.NON_AUTHORITATIVE_INFORMATION, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow10() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.NO_CONTENT);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.NO_CONTENT, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow11() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.RESET_CONTENT);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.RESET_CONTENT, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow12() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.PARTIAL_CONTENT);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.PARTIAL_CONTENT, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow13() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.MULTI_STATUS);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.MULTI_STATUS, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow14() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.ALREADY_REPORTED);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.ALREADY_REPORTED, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow15() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.IM_USED);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.IM_USED, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(String, HttpStatus)}
     */
    @Test
    void testRestThrow16() {
        RestException actualRestThrowResult = RestException.restThrow("User Msg", HttpStatus.MULTIPLE_CHOICES);
        assertEquals("User Msg", actualRestThrowResult.getUserMsg());
        assertEquals(HttpStatus.MULTIPLE_CHOICES, actualRestThrowResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow17() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.CONTINUE);
        assertEquals(HttpStatus.CONTINUE, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow18() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.SWITCHING_PROTOCOLS);
        assertEquals(HttpStatus.SWITCHING_PROTOCOLS, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow19() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.PROCESSING);
        assertEquals(HttpStatus.PROCESSING, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow20() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.CHECKPOINT);
        assertEquals(HttpStatus.CHECKPOINT, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow21() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.OK);
        assertEquals(HttpStatus.OK, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow22() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.CREATED);
        assertEquals(HttpStatus.CREATED, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow23() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.ACCEPTED);
        assertEquals(HttpStatus.ACCEPTED, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow24() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(),
                HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        assertEquals(HttpStatus.NON_AUTHORITATIVE_INFORMATION, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow25() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.NO_CONTENT);
        assertEquals(HttpStatus.NO_CONTENT, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow26() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.RESET_CONTENT);
        assertEquals(HttpStatus.RESET_CONTENT, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow27() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.PARTIAL_CONTENT);
        assertEquals(HttpStatus.PARTIAL_CONTENT, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow28() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.MULTI_STATUS);
        assertEquals(HttpStatus.MULTI_STATUS, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow29() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.ALREADY_REPORTED);
        assertEquals(HttpStatus.ALREADY_REPORTED, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow30() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.IM_USED);
        assertEquals(HttpStatus.IM_USED, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow31() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.MULTIPLE_CHOICES);
        assertEquals(HttpStatus.MULTIPLE_CHOICES, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow32() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.MOVED_PERMANENTLY);
        assertEquals(HttpStatus.MOVED_PERMANENTLY, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow33() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.FOUND);
        assertEquals(HttpStatus.FOUND, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow34() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.MOVED_TEMPORARILY);
        assertEquals(HttpStatus.MOVED_TEMPORARILY, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow35() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.SEE_OTHER);
        assertEquals(HttpStatus.SEE_OTHER, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#restThrow(List, HttpStatus)}
     */
    @Test
    void testRestThrow36() {
        RestException actualRestThrowResult = RestException.restThrow(new ArrayList<>(), HttpStatus.NOT_MODIFIED);
        assertEquals(HttpStatus.NOT_MODIFIED, actualRestThrowResult.getStatus());
        assertTrue(actualRestThrowResult.getErrors().isEmpty());
    }

    /**
     * Method under test: {@link RestException#notFound(String)}
     */
    @Test
    void testNotFound() {
        RestException actualNotFoundResult = RestException.notFound("Resource Key");
        assertEquals("RESOURCE KEY_NOT_FOUND", actualNotFoundResult.getUserMsg());
        assertEquals(HttpStatus.NOT_FOUND, actualNotFoundResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#otherServiceError(String)}
     */
    @Test
    void testOtherServiceError() {
        RestException actualOtherServiceErrorResult = RestException.otherServiceError("Service Name");
        assertEquals("ERROR_IN_$S", actualOtherServiceErrorResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualOtherServiceErrorResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#badRequest(String)}
     */
    @Test
    void testBadRequest() {
        RestException actualBadRequestResult = RestException.badRequest("Resource Key");
        assertEquals("Resource Key", actualBadRequestResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualBadRequestResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#alreadyExists(String)}
     */
    @Test
    void testAlreadyExists() {
        RestException actualAlreadyExistsResult = RestException.alreadyExists("Resource Key");
        assertEquals("RESOURCE KEY_ALREADY_EXISTS", actualAlreadyExistsResult.getUserMsg());
        assertEquals(HttpStatus.CONFLICT, actualAlreadyExistsResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#attackResponse()}
     */
    @Test
    void testAttackResponse() {
        RestException actualAttackResponseResult = RestException.attackResponse();
        assertEquals("ATTACK_RESPONSE", actualAttackResponseResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualAttackResponseResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#forbidden()}
     */
    @Test
    void testForbidden() {
        RestException actualForbiddenResult = RestException.forbidden();
        assertEquals("FORBIDDEN", actualForbiddenResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualForbiddenResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#forbidden(List)}
     */
    @Test
    void testForbidden2() {
        RestException actualForbiddenResult = RestException.forbidden(new ArrayList<>());
        assertEquals("Forbidden. You don't have permissions : []", actualForbiddenResult.getUserMsg());
        assertEquals(HttpStatus.BAD_REQUEST, actualForbiddenResult.getStatus());
    }

    /**
     * Method under test: {@link RestException#internalServerError()}
     */
    @Test
    void testInternalServerError() {
        RestException actualInternalServerErrorResult = RestException.internalServerError();
        assertEquals("INTERNAL_SERVER_ERROR", actualInternalServerErrorResult.getUserMsg());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualInternalServerErrorResult.getStatus());
    }
}

