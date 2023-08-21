package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;

public interface UserServiceInterface {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getUserDetails(Long userId) throws UserNotFoundException;

}
