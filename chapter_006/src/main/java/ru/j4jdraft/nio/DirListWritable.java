package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Display a directory of only those files that are writable.
 */
public class DirListWritable {
    public static void main(String[] args) {
        String dirname = ".";
        Path dirPath = Paths.get(dirname);
        DirectoryStream.Filter<Path> filter = Files::isWritable;
        System.out.println("Writable entries in directory of " + dirPath.toAbsolutePath().normalize());
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath, filter)) {
            for (Path entry : dirStream) {
                if (Files.isDirectory(entry)) {
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
