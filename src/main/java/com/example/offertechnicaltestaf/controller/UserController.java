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

    @GetMapping("/displayUser")
    public ResponseEntity<?> getUserById(@RequestParam String name) {
        User user = userService.getUserByName(name);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
