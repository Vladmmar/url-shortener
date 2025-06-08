package com.vladm.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {

    private static final String URL = PropertiesUtil.get("db.url");
    private static final String USER = PropertiesUtil.get("db.user");
    private static final String PASSWORD = PropertiesUtil.get("db.password");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static Connection open() {
        return DriverManager.getConnection(
                URL, USER, PASSWORD
        );
    }
}
