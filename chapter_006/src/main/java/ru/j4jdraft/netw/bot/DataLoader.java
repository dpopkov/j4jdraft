package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataLoader {
    private Map<String, String> map;

    public void loadFromXML(InputStream in) throws IOException {
        Properties data = new Properties();
        data.loadFromXML(in);
        map = new HashMap<>();
        for (String key : data.stringPropertyNames()) {
            map.put(key, data.getProperty(key));
        }
    }

    public Map<String, String> getMap() {
        return map;
    }
}
