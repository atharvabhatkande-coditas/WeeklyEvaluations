package com.coditas.NewJWTAssignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Username Must not be empty")
    private String username;
    @Size(min = 6,message = "Password Min length required is 6")
    private String password;
    private boolean enabled;
    @Email(message = "Invalid Email Format")
    private String email;
    private String role;
}
