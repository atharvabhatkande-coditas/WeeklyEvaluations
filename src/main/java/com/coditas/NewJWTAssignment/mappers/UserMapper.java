package com.coditas.NewJWTAssignment.mappers;

import com.coditas.NewJWTAssignment.dto.UserDto;
import com.coditas.NewJWTAssignment.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(UserDto userDto);
    UserDto toDto(Users user);
}
