package com.example.offertechnicaltestaf.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDTO {
    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "A birth date is required")
    private LocalDate birthDate;

    @NotBlank(message = "The country of residence is required")
    private String countryOfResidence;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number should be 10 digits")
    private String phoneNumber;

    private String gender;
}
