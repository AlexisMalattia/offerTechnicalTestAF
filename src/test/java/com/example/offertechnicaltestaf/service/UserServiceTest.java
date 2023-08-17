package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    void isUserValid_true() {
        User user = new User();
        user.setName("John");
        user.setBirthDate(Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");
        userService.createUser(user);
        System.out.println(user);
        User savedUser = userRepository.findById(user.getId()).orElse(null);
        System.out.println(savedUser);
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getBirthDate(), savedUser.getBirthDate());
        assertEquals(user.getCountryOfResidence(), savedUser.getCountryOfResidence());
        //verify(userRepository, times(1)).save(user);
    }

    @Test
    void isUserValid_false() {
        User user = new User();
        user.setName("");
        try {
            userService.createUser(user);
        } catch (InvalidInformationsFromUsersException e) {
            ValidateUserParams validateUserParams = e.getValidateUserParams();
            assertTrue(validateUserParams.hasErrors());
            Map<String,String> expectedErrors = new HashMap<>();
            expectedErrors.put("name", "A name is required");
            expectedErrors.put("countryOfResidence", "A country of residence is required");
            expectedErrors.put("birthDate", "A birth date is required");
            assertEquals(expectedErrors, validateUserParams.getErrors());
        }

    }
}