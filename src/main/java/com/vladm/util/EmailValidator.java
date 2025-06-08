package com.vladm.util;

import com.vladm.validator.Error;
import com.vladm.validator.ValidationResult;
import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

import static com.vladm.util.ErrorUtil.INVALID_EMAIL;

@UtilityClass
public class EmailValidator {

    public static boolean isValid(String email) {
        return Pattern.matches("^[^@]+@[^@]+\\.[^@]+$", email);
    }
}
