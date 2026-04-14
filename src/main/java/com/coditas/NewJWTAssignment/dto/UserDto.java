package com.coditas.NewJWTAssignment.dto;

import com.coditas.NewJWTAssignment.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Username Must not be empty")
    private String username;
    private String password;
    private boolean enabled;
    Set<RoleType> roles=new HashSet<>();
}
