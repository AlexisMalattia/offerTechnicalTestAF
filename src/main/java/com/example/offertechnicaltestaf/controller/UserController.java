package com.example.offertechnicaltestaf.controller;

import com.example.offertechnicaltestaf.dto.UserDTO;
import com.example.offertechnicaltestaf.exception.UserNotFoundException;
import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This function aims to add a new user to the database
     * @param userDTO The user we are trying to add to the database
     * @return Returns an HTML status, 201 if the user has been created in the database, 400 if there's an error
     */
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        if(!UserUtils.isAdult(userDTO.getBirthDate()) || !UserUtils.isFrenchResident(userDTO.getCountryOfResidence())){
            return new ResponseEntity<>("Only adult French residents are allowed to create an account", HttpStatus.BAD_REQUEST);
        }
        UserDTO savedUserDTO = userService.saveUser(userDTO);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    /**
     * This function aims to retrieve an existing user from the database thanks to its ID
     * @param id the id of the user we are trying to retrieves from the database
     * @return an HTTP status, 200 if the id matches one in the database, 404 otherwise.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            UserDTO userDTO = userService.getUserDetails(userId);
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
