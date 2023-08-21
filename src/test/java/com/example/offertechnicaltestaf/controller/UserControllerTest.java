package com.example.offertechnicaltestaf.controller;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;
import com.example.offertechnicaltestaf.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testCreateUser_ValidUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2001,4,22));
        userDTO.setCountryOfResidence("France");
        when(userService.saveUser(userDTO)).thenReturn(userDTO);
        ResponseEntity<?> response = userController.createUser(userDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testCreateUser_NonFrenchResident() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2001,4,22));
        userDTO.setCountryOfResidence("Allemagne");
        ResponseEntity<?> response = userController.createUser(userDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateUser_NonAdult() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2009,4,22));
        userDTO.setCountryOfResidence("France");
        ResponseEntity<?> response = userController.createUser(userDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetUserById_UserFound() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2001,4,22));
        userDTO.setCountryOfResidence("France");
        when(userService.getUserDetails(userId)).thenReturn(userDTO);
        ResponseEntity<?> response = userController.getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Long userId = 1L;
        when(userService.getUserDetails(userId)).thenThrow(new UserNotFoundException("User not found with this ID : " + userId));
        ResponseEntity<?> response = userController.getUserById(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}