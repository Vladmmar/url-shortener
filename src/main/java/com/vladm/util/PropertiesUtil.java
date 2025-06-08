package com.vladm.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String[] CONFIG_FILES;
    private static final String[] DEFAULT_CONFIG_FILES =
            {"application.properties", "app.properties", "config.properties"};

    static {
        CONFIG_FILES = System.getProperty("config_files").split(",");
        loadProperties(CONFIG_FILES);
        loadProperties(DEFAULT_CONFIG_FILES);
    }

    private static void loadProperties(String... configFiles) {
        for (var configFile : configFiles) {
            try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(configFile)) {
                if (inputStream != null) {
                    PROPERTIES.load(inputStream);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
