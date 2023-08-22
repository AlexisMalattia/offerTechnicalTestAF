package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;
import com.example.offertechnicaltestaf.mapper.UserMapper;
import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTestWithHDatabase {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSaveUserAndGetDetails() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2001, 4, 22));
        userDTO.setCountryOfResidence("France");

        UserDTO savedUserDTO = userService.saveUser(userDTO);
        assertNotNull(savedUserDTO.getId());
        assertEquals(userDTO.getName(), savedUserDTO.getName());
        User savedUserEntity = userRepository.findById(savedUserDTO.getId()).orElse(null);
        assertNotNull(savedUserEntity);
        assertEquals(savedUserDTO.getName(), savedUserEntity.getName());
        assertEquals(savedUserDTO.getBirthDate(), savedUserEntity.getBirthDate());
        assertEquals(savedUserDTO.getCountryOfResidence(), savedUserEntity.getCountryOfResidence());
    }

    @Test
    public void testGetUserDetails_NotFound(){
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserDetails(10000L);
        });

    }
}