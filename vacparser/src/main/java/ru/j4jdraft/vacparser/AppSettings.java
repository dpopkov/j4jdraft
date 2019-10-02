package ru.j4jdraft.vacparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppSettings {
    private static final Logger LOG = LoggerFactory.getLogger(AppSettings.class);
    private static final int DEFAULT_CRAWL_DELAY = 2000;

    private final Properties properties = new Properties();

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

    public int crawlDelay() {
        String delayStr = properties.getProperty("crawl.delay");
        try {
            int delay = Integer.parseInt(delayStr);
            LOG.info("Using crawl.delay {} ms", delay);
            return delay;
        } catch (NumberFormatException nfe) {
            LOG.warn("Could not parse crawl.delay, using default: {}", DEFAULT_CRAWL_DELAY);
            return DEFAULT_CRAWL_DELAY;
        }
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
