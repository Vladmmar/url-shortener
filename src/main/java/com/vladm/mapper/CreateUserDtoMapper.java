package com.vladm.mapper;

import com.vladm.dto.CreateUserDto;
import com.vladm.entity.User;
import com.vladm.util.HashUtil;
import com.vladm.util.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access=PRIVATE)
public class CreateUserDtoMapper {

    private static final CreateUserDtoMapper INSTANCE = new CreateUserDtoMapper();

    public User mapFrom(CreateUserDto o) {
        return User.builder()
                .firstName(o.getFirstName())
                .lastName(o.getLastName())
                .email(o.getEmail())
                .password(HashUtil.hashpw(o.getPassword()))
                .profileImage(o.getProfileImage().getSubmittedFileName())
                .birthday(LocalDateFormatter.format(o.getBirthday()))
                .build();
    }

    public static CreateUserDtoMapper getInstance() {
        return INSTANCE;
    }
}
