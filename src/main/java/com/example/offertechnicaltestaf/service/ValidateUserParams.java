package com.example.offertechnicaltestaf.service;

import java.util.HashMap;
import java.util.Map;
public class ValidateUserParams {
    private Map<String, String> errors = new HashMap<>();
    public void addError(String input, String error) {
        errors.put(input, error);
    }
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    public Map<String, String> getErrors() {
        return errors;
    }
}