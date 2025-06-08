package com.vladm.service;

import com.vladm.dao.UserDao;
import com.vladm.dto.CreateUserDto;
import com.vladm.entity.User;
import com.vladm.exception.ValidationException;
import com.vladm.mapper.CreateUserDtoMapper;
import com.vladm.util.HashUtil;
import com.vladm.validator.CreateUserValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserDtoMapper createUserDtoMapper = CreateUserDtoMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        // Validate the input, except profile image

        var validationResult = createUserValidator.validate(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        // Map from CreateUserDto to User
        var user = createUserDtoMapper.mapFrom(userDto);

        // Save the profile image
        imageService.upload(user.getProfileImage(), userDto.getProfileImage().getInputStream());

        // Save to database and return user ID

        return userDao.save(user).getId();
    }

    public boolean login(String email, String password) {
        String hashedPassword = userDao.findByEmail(email)
                .map(User::getPassword)
                .orElse("");
        return HashUtil.checkpw(password, hashedPassword);
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
