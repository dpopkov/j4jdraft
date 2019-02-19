package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Displays a directory.
 */
public class DirList {
    public static void main(String[] args) {
        String dirname = ".";
        Path dirPath = Paths.get(dirname);
        System.out.println("Directory of " + dirPath.toAbsolutePath().normalize());
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath)) {
            for (Path entry : dirStream) {
                BasicFileAttributes attr = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attr.isDirectory()) {
                    System.out.print("<DIR> ");
                } else {
                    System.out.print("      ");
                }
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
