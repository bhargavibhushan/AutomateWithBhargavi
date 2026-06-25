package com.automationexercise.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static void initConfig() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(
                "src/main/resources/config.properties");
            prop.load(fis);
            System.out.println("Config file loaded successfully!");
        } catch (IOException e) {
            System.out.println("Config file not found: " 
                + e.getMessage());
        }
    }

    public static String get(String key) {
        if (prop == null) {
            initConfig();
        }
        return prop.getProperty(key);
    }

    // Specific methods for easy access
    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static String getValidEmail() {
        return get("validEmail");
    }

    public static String getValidPassword() {
        return get("validPassword");
    }

    public static String getInvalidEmail() {
        return get("invalidEmail");
    }

    public static String getInvalidPassword() {
        return get("invalidPassword");
    }

    public static String getEnvironment() {
        return get("environment");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicitWait"));
    }
}