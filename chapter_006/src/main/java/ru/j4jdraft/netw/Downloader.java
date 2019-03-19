package ru.j4jdraft.netw;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Downloader url path");
            return;
        }
        URL url = new URL(args[0]);
        Path path = Paths.get(args[1]);
        long bytes;
        System.out.printf("Opening %s%n", url);
        try (InputStream in = url.openStream()) {
            bytes = Files.copy(in, path);
        }
        System.out.printf("%d bytes read%n", bytes);
    }
}
