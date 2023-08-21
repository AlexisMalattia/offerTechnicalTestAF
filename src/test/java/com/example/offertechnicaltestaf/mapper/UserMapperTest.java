package com.example.offertechnicaltestaf.mapper;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Alexis");
        user.setBirthDate(LocalDate.of(2001, 4, 22));
        user.setCountryOfResidence("France");
        UserDTO userDTO = userMapper.toDTO(user);
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getBirthDate(), userDTO.getBirthDate());
        assertEquals(user.getCountryOfResidence(), userDTO.getCountryOfResidence());
    }

    @Test
    void toEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alexis");
        userDTO.setBirthDate(LocalDate.of(2001, 4, 22));
        userDTO.setCountryOfResidence("France");
        User user = userMapper.toEntity(userDTO);
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getBirthDate(), userDTO.getBirthDate());
        assertEquals(user.getCountryOfResidence(), userDTO.getCountryOfResidence());
    }
}