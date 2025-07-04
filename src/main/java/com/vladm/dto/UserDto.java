package com.vladm.dto;

import com.vladm.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String profileImage;
    String birthday;
    Role role;
}
