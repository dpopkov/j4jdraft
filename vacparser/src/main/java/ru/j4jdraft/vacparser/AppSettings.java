package ru.j4jdraft.vacparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppSettings {
    private Properties properties = new Properties();

    public AppSettings(String fileName) {
        loadFrom(fileName);
    }

    public String siteUrl() {
        return properties.getProperty("site.url");
    }

    public String jdbcUrl() {
        return properties.getProperty("jdbc.url");
    }

    public String jdbcUser() {
        return properties.getProperty("jdbc.user");
    }

    public String jdbcPassword() {
        return properties.getProperty("jdbc.password");
    }

    public String cronTime() {
        return properties.getProperty("cron.time");
    }

    private void loadFrom(String propName) {
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(propName);
            if (input != null) {
                properties.load(input);
            } else {
                throw new IllegalArgumentException("Cannot read properties file: " + propName);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading properties", e);
        }
    }
}
