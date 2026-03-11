package com.example.SbHibernate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Firstname cannot be null")
    private String firstName;
    @NotBlank(message = "Username cannot be null")
    private String userName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 8,message = "Min password length required is 8")
    private String password;
    @Size(min = 10,message = "Phone number less than 10 digits")
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
}
