package com.vladm.util;

import com.vladm.dto.CreateUserDto;
import com.vladm.validator.ValidationResult;
import lombok.experimental.UtilityClass;

import static com.vladm.util.ErrorUtil.INVALID_FIRST_NAME;
import static com.vladm.util.ErrorUtil.INVALID_LAST_NAME;

@UtilityClass
public class NamesValidator {

    public static void validateNames(CreateUserDto userDto, ValidationResult validationResult) {
        var fNameLength = userDto.getFirstName().length();
        var lNameLength = userDto.getLastName().length();
        if (fNameLength < 2 || fNameLength > 30) {
            validationResult.add(INVALID_FIRST_NAME);
        }

        if (lNameLength < 2 || lNameLength > 30) {
            validationResult.add(INVALID_LAST_NAME);
        }
    }
}
