package ru.j4jdraft.io.demos2;

import java.io.File;

/**
 * Demonstrates usage of class {@code File}.
 */
public class FileDemo {
    public static void main(String[] args) {
        String pathname = "pom.xml";
        if (args.length == 1) {
            pathname = args[0];
        }
        File f = new File(pathname);
        print("File name: ", f.getName());
        print("Path: ", f.getPath());
        print("Abs Path: ", f.getAbsolutePath());
        print("Parent: ", f.getParent());
        print("Exists: ", f.exists());
        print("Writable: ", f.canWrite());
        print("Readable: ", f.canRead());
        print("Directory: ", f.isDirectory());
        print("Normal file: ", f.isFile());
        print("Absolute: ", f.isAbsolute());
        print("File last modified: ", f.lastModified());
        print("File size: ", f.length());
    }

    private static void print(String label, String text) {
        System.out.print(label);
        System.out.println(text);
    }

    private static void print(String label, boolean flag) {
        print(label, Boolean.toString(flag));
    }

    private static void print(String label, long value) {
        print(label, Long.toString(value));
    }
}
