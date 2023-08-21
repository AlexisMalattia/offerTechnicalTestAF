package com.example.offertechnicaltestaf.mapper;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
