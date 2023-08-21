package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;
import com.example.offertechnicaltestaf.mapper.UserMapper;
import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {
   @InjectMocks
    private UserService userService;
   @Mock
    private UserRepository userRepository;
   @Mock
    private UserMapper userMapper;

   @Test
    public void testGetUserDetails_UserFound() {
       Long userId = 1L;
       User user = new User();
       user.setId(userId);
       user.setName("Alexis");
       UserDTO expectedUserDTO = new UserDTO();
       expectedUserDTO.setName("Alexis");

       when(userRepository.findById(userId)).thenReturn(Optional.of(user));
       when(userMapper.toDTO(user)).thenReturn(expectedUserDTO);
       UserDTO retrievedUserDTO = userService.getUserDetails(userId);
       assertEquals(expectedUserDTO.getName(), retrievedUserDTO.getName());
   }

   @Test
    public void testGetUserDetails_UserNotFound() {
       Long userId = 1L;
       when(userRepository.findById(userId)).thenReturn(Optional.empty());
       assertThrows(UserNotFoundException.class, () -> userService.getUserDetails(userId));
   }

   @Test
    public void testSaveUser(){
       UserDTO userDTO = new UserDTO();
       userDTO.setName("Alexis");
       userDTO.setBirthDate(LocalDate.of(2001, 4, 22));
       userDTO.setCountryOfResidence("France");

       User userEntity = new User();
       userEntity.setName("Alexis");
       userEntity.setBirthDate(LocalDate.of(2001, 4, 22));
       userEntity.setCountryOfResidence("France");

       when(userMapper.toEntity(userDTO)).thenReturn(userEntity);
       when(userRepository.save(userEntity)).thenReturn(userEntity);

       UserDTO expectedUserDTO = new UserDTO();
       expectedUserDTO.setName("Alexis");
       expectedUserDTO.setBirthDate(LocalDate.of(2001, 4, 22));
       expectedUserDTO.setCountryOfResidence("France");

       when(userMapper.toDTO(userEntity)).thenReturn(expectedUserDTO);
       UserDTO savedUserDTO = userService.saveUser(userDTO);

       assertEquals(expectedUserDTO, savedUserDTO);
   }
}
