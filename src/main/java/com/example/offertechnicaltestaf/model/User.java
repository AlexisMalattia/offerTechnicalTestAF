package com.example.offertechnicaltestaf.model;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String countryOfResidence;
    private String phoneNumber;
    private String gender;
}
