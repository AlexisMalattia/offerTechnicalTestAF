package com.example.offertechnicaltestaf.utils;

import java.time.LocalDate;
import java.time.Period;

public class UserUtils {
    private static final int ADULT_AGE = 18;
    public static boolean isAdult(LocalDate birthdate){
        return Period.between(birthdate, LocalDate.now()).getYears() >= ADULT_AGE;
    }

    public static boolean isFrenchResident(String country){
        return "France".equalsIgnoreCase(country);
    }
}
