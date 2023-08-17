package com.example.offertechnicaltestaf.service;

import java.util.HashMap;
import java.util.Map;
public class ValidateUserParams {
    private Map<String, String> errors = new HashMap<>();

    /**
     * add a new element in the HashMap "errors" if a specific input is not valid
     * @param input the name of the input related to the error
     * @param error the error message that explains the reason why the input is not valid
     */
    public void addError(String input, String error) {
        errors.put(input, error);
    }

    /**
     * Checks it the HashMap "errors" contains an error
     * @return false if the hashMap is empty, true otherwise
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    public Map<String, String> getErrors() {
        return errors;
    }
}