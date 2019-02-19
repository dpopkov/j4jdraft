package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Obtains information about a path and a file.
 */
public class PathDemo {
    public static void main(String[] args) {
        Path filepath = Paths.get("txt/test.txt");
        System.out.println("File Name: " + filepath.getName(1));
        System.out.println("Absolute Path: " + filepath.toAbsolutePath());
        System.out.println("Parent: " + filepath.getParent());
        if (Files.exists(filepath)) {
            System.out.println("File exists");
        } else {
            System.out.println("File does not exist");
        }
        try {
            if (Files.isHidden(filepath)) {
                System.out.println("File is hidden");
            } else {
                System.out.println("File is not hidden");
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        if (Files.isWritable(filepath)) {
            System.out.println("File is writable");
        }
        if (Files.isReadable(filepath)) {
            System.out.println("File is readable");
        }
        try {
            BasicFileAttributes attr = Files.readAttributes(filepath, BasicFileAttributes.class);
            if (attr.isDirectory()) {
                System.out.println("The file is a directory");
            } else {
                System.out.println("The file is not a directory");
            }
            if (attr.isRegularFile()) {
                System.out.println("The file is a normal file");
            } else {
                System.out.println("The file is not a normal file");
            }
            if (attr.isSymbolicLink()) {
                System.out.println("The file is a symbolic link");
            } else {
                System.out.println("The file is not a symbolic link");
            }
            System.out.println("File last modified: " + attr.lastModifiedTime());
            System.out.println("File size: " + attr.size() + " bytes");
        } catch (IOException e) {
            System.out.println("Error reading attributes: " + e);
        }
    }
}
