package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void isUserValid_true() {
        User user = new User();
        user.setName("John");
        user.setBirthDate(Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setCountryOfResidence("France");
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void deleteUser() {
    }
}