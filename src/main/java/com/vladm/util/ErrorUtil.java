package com.vladm.util;

import lombok.experimental.UtilityClass;
import com.vladm.validator.Error;

@UtilityClass
public class ErrorUtil {

    public static final Error INVALID_FIRST_NAME = Error.of("INVALID_FIRST_NAME", "Invalid first name");
    public static final Error INVALID_LAST_NAME = Error.of("INVALID_LAST_NAME", "Invalid last name");
    public static final Error INVALID_EMAIL = Error.of("INVALID_EMAIL", "Invalid email");
    public static final Error INVALID_DATE = Error.of("INVALID_DATE", "Invalid date format");
    public static final Error INVALID_CONFIRMATION_PASSWORD = Error.of("INVALID_CONFIRMATION_PASSWORD", "Confirmation password is different from the original one");
}
