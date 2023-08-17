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

    /**
     * This function validates the user's attributes, ensuring that each non-optional attribute has a value and
     * that the value meets the requirements.
     * @param user The user that has to be verified by this function
     * @return a ValidateUserParams that contains the errors if they exist
     */
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

    /**
     *
     * @param user The user that we are trying to add to the database, it validates the attributes of the user thanks to
     *             isUserValid() and then save it in the database if no error has been thrown
     */
    public void createUser(User user) {
        ValidateUserParams validateUserParams = isUserValid(user);
        if (validateUserParams.hasErrors()) {
            throw new InvalidInformationsFromUsersException(validateUserParams);
        }
        userRepository.save(user);
    }

    /**
     *
     * @param id The id generated by the application when the user has been added to the database.
     * @return returns a user if the Id matches an element of the database, returns nulls otherwise.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
