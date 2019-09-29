package ru.j4jdraft.vacparser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceReader {

    public static String read(String name, Charset charset) throws URISyntaxException, IOException {
        URL url = ResourceReader.class.getClassLoader().getResource(name);
        if (url != null) {
            return Files.readString(Paths.get(url.toURI()), charset);
        } else {
            throw new IllegalArgumentException("Cannot open resource: " + name);
        }
    }
}
