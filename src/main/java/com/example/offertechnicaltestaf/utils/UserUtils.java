package com.example.offertechnicaltestaf.utils;

import java.time.LocalDate;
import java.time.Period;

public class UserUtils {
    private static final int ADULT_AGE = 18;

    /**
     * Check if the user is an adult
     * @param birthdate The birthdate of the user
     * @return true if the user is an adult, false otherwise
     */
    public static boolean isAdult(LocalDate birthdate){
        return Period.between(birthdate, LocalDate.now()).getYears() >= ADULT_AGE;
    }

    /**
     * Check if the user is a french resident
     * @param country The country of the user
     * @return true if the user is a french resident, false otherwise
     */
    public static boolean isFrenchResident(String country){
        return "France".equalsIgnoreCase(country);
    }
}
