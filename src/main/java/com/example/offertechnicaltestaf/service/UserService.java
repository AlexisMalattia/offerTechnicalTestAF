package com.example.offertechnicaltestaf.service;

import com.example.offertechnicaltestaf.model.User;
import com.example.offertechnicaltestaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ValidateUserParams isUserValid(User user) {
        ValidateUserParams validateUserParams = new ValidateUserParams();
        if (user.getName() == null || user.getName().isEmpty()) {
            validateUserParams.addError("name", "A name is required");
        }
        if (user.getBirthDate() == null) {
            validateUserParams.addError("birthDate", "A birth date is required");
        } else {
            LocalDate today = LocalDate.now();
            LocalDate birthDate = user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // this converts Date to LocalDate, it seems that it is a better way to do it. Solution found on https://stackoverflow.com/questions/21242110/convert-java-util-date-to-java-time-localdate
            int age = today.getYear() - birthDate.getYear();
            if(age < 18){
                validateUserParams.addError("birthDate", "The user must be at least 18 years old");
            }
        }
        if (user.getCountryOfResidence() == null || user.getCountryOfResidence().isEmpty()) {
            validateUserParams.addError("countryOfResidence", "A country of residence is required");
        } else if (!user.getCountryOfResidence().equals("France")) {
            validateUserParams.addError("countryOfResidence", "The country of residence must be France to create an account");
        }
        return validateUserParams;
    }
    public void createUser(User user) {
        ValidateUserParams validateUserParams = isUserValid(user);
        if (validateUserParams.hasErrors()) {
            throw new InvalidInformationsFromUsersException(validateUserParams);
        }
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
