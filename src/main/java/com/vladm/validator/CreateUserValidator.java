package com.vladm.validator;

import com.vladm.dto.CreateUserDto;
import com.vladm.dto.UserDto;
import com.vladm.util.*;
import lombok.NoArgsConstructor;

import static com.vladm.util.ErrorUtil.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access=PRIVATE)
public class CreateUserValidator {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult validate(CreateUserDto userDto) {
        // create validation result
        ValidationResult validationResult = new ValidationResult();

        // check if the confirmation password is correct
        if (!userDto.getPassword().equals(userDto.getConfirmationPassword())) {
            validationResult.add(ErrorUtil.INVALID_CONFIRMATION_PASSWORD);
        }

        // validate user's email
        if (EmailValidator.isValid(userDto.getEmail())) {
            validationResult.add(INVALID_EMAIL);
        }

        // check whether names are valid
        NamesValidator.validateNames(userDto, validationResult);

        // validate birthday date
        if (!LocalDateFormatter.isValid(userDto.getBirthday())) {
            validationResult.add(INVALID_DATE);
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
