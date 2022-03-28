package com.epam.tc.hw3.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertiesProvider {

    private static final String PROPERTIES_NAME = "test.properties";
    private static Properties properties;

    @SneakyThrows
    public PropertiesProvider() {
        try (InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Incorrect file path to properties");
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}
