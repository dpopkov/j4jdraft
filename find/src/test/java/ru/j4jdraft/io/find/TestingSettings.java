package ru.j4jdraft.io.find;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestingSettings {
    private final static Properties props = new Properties();

    static {
        InputStream in = TestingSettings.class.getResourceAsStream("/test.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String testDirProvider() {
        return props.getProperty("testDirProvider");
    }
}
