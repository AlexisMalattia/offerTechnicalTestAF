package com.example.offertechnicaltestaf.service;

public class InvalidInformationsFromUsersException extends RuntimeException {
    private final ValidateUserParams validateUserParams;
    public InvalidInformationsFromUsersException(ValidateUserParams validateUserParams) {
        this.validateUserParams = validateUserParams;
    }
    public ValidateUserParams getValidateUserParams() {
        return validateUserParams;
    }
}
