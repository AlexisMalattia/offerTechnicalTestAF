package com.example.offertechnicaltestaf.controller;

import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.service.InvalidInformationsFromUsersException;
import com.example.offertechnicaltestaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This function aims to add a new user to the database
     * @param user The user we are trying to add to the database
     * @return Returns an HTML status, 201 if the user has been created in the database, 400 if there's an error
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } catch (InvalidInformationsFromUsersException e) {
            Map<String,String> errors = e.getValidateUserParams().getErrors();
            List<String> errorMessages = new ArrayList<>(errors.values());
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This function aims to retrieve an existing user from the database thanks to its ID
     * @param id the id of the user we are trying to retrieves from the database
     * @return an HTTP status, 200 if the id matches one in the database, 404 otherwise.
     */
    @GetMapping("/displayUser")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        // User user = userService.getUserByName(name);
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
