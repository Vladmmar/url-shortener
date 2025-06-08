package com.vladm.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class HashUtil {

    public static String hashpw(String password) {
        var salt = BCrypt.gensalt(16);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkpw(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
