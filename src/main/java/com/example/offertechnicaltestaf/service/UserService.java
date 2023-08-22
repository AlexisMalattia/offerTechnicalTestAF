package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;
import com.example.offertechnicaltestaf.mapper.UserMapper;
import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    /**
     * This function saves a user in the database
     * @param userDTO : the user to save
     * @return the saved user
     */
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User userEntity = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(userEntity);
        return userMapper.toDTO(savedUser);
    }

    /**
     * This function returns all the information about a user from the database, throws an exception if the user is not found
     * @param userId : the id of the user
     * @return the user
     */
    @Override
    public UserDTO getUserDetails(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.toDTO(user);
        } else {
            throw new UserNotFoundException("User not found with this ID : " + userId);
        }
    }
}
