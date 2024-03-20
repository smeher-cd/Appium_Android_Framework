package com.ikea.homesmart.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;

    public PropertyReader(String configFilePath) {
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream(configFilePath)) {

            if (inputStream == null) {
                System.out.println("Sorry, unable to find properties file");
                return;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);
        }
    }

}


