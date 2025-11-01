package com.swaggerpetstore.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static String environment;

    // Static block to initialize based on environment
    static {
        //System.setProperty("env", "qa"); // Default environment, can be overridden by system property
        String env = System.getProperty("env");
        if (env == null || env.isEmpty()) {
            throw new IllegalArgumentException("Environment property 'env' must be set");
        }
        setEnvironment(env);
    }

    private ConfigManager() {}

    public static void setEnvironment(String env) {
        environment = env;
        loadPropertiesForEnvironment();
    }

    private static void loadPropertiesForEnvironment() {
        // Define the file path based on the environment
        String configFile = "src/main/resources/application-" + environment + ".properties";

        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load properties for environment: " + environment, ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
