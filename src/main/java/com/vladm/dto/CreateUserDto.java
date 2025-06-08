package com.vladm.dto;

import com.vladm.entity.Role;
import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class CreateUserDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmationPassword;
    Part profileImage;
    String birthday;
}
