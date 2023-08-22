package com.example.offertechnicaltestaf.mapper;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Map User to UserDTO
     * @param user The user to map
     * @return The mapped user
     */
    UserDTO toDTO(User user);

    /**
     * Map UserDTO to User
     * @param userDTO The userDTO to map
     * @return The mapped userDTO
     */
    User toEntity(UserDTO userDTO);
}
