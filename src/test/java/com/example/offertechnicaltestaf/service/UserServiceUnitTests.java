package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceUnitTests {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void isUserValid_emptyName() {
        User user = new User();
        user.setName("");
        user.setBirthDate(Date.from(LocalDate.of(2001, 4, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");

        ValidateUserParams validateUserParams = userService.isUserValid(user);
        assertTrue(validateUserParams.hasErrors());
        assertTrue(validateUserParams.getErrors().containsKey("name"));
    }

    @Test
    void isUserValid_isLessThan18(){
        User user = new User();
        user.setName("Alexis");
        user.setBirthDate(Date.from(LocalDate.of(2023, 4, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");

        ValidateUserParams validateUserParams = userService.isUserValid(user);
        assertTrue(validateUserParams.hasErrors());
        assertTrue(validateUserParams.getErrors().containsKey("birthDate"));
    }

    @Test
    void isUserValid_isNotFrench(){
        User user = new User();
        user.setName("Alexis");
        user.setBirthDate(Date.from(LocalDate.of(2001, 4, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("Italian");

        ValidateUserParams validateUserParams = userService.isUserValid(user);
        assertTrue(validateUserParams.hasErrors());
        assertTrue(validateUserParams.getErrors().containsKey("countryOfResidence"));
    }

    @Test
    void isUserValid_validUser(){
        User user = new User();
        user.setName("Alexis");
        user.setBirthDate(Date.from(LocalDate.of(2001, 4, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");

        ValidateUserParams validateUserParams = userService.isUserValid(user);
        assertFalse(validateUserParams.hasErrors());
    }

    @Test
    void createUser_validUser(){
        User user = new User();
        user.setName("Alexis");
        user.setBirthDate(Date.from(LocalDate.of(2001, 4, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createUser_invalidUser(){
        User user = new User();
        user.setName("Alexis");
        assertThrows(InvalidInformationsFromUsersException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(user);
    }
}
