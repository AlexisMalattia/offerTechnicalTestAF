package com.example.offertechnicaltestaf.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUserParamsTest {

    @Test
    void addError() {
        ValidateUserParams validateUserParams = new ValidateUserParams();
        validateUserParams.addError("name", "A name is required");
        assertTrue(validateUserParams.hasErrors());
        Map<String,String> errors = validateUserParams.getErrors();
        assertTrue(errors.containsKey("name"));
        assertEquals("A name is required", errors.get("name"));
    }

    @Test
    void hasErrors_noError() {
        ValidateUserParams validateUserParams = new ValidateUserParams();
        assertFalse(validateUserParams.hasErrors());
    }

    @Test
    void hasErrors_withError() {
        ValidateUserParams validateUserParams = new ValidateUserParams();
        validateUserParams.addError("name", "A name is required");
        assertTrue(validateUserParams.hasErrors());
    }

    @Test
    void getErrors() {
        ValidateUserParams validateUserParams = new ValidateUserParams();
        validateUserParams.addError("name", "A name is required");
        validateUserParams.addError("countryOfResidence", "A country of residence is required");
        validateUserParams.addError("birthDate", "A birth date is required");
        Map<String,String> expectedErrors = Map.of(
                "name", "A name is required",
                "countryOfResidence", "A country of residence is required",
                "birthDate", "A birth date is required"
        );
        assertEquals(expectedErrors, validateUserParams.getErrors());
    }
}