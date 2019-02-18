package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Demonstrates copying a file using NIO.
 */
public class NioCopy {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: NioCopy fromPath toPath");
            return;
        }
        Path source = Paths.get(args[0]);
        Path target = Paths.get(args[1]);
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
