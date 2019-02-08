package ru.j4jdraft.io.demos4;

import ru.j4jdraft.io.common.PathTools;

import java.io.*;
import java.nio.file.Paths;

public class FileReaderDemo {
    public static void main(String[] args) {
        String sourceRoot = Paths.get("chapter_006", "src", "main", "java").toString();
        String path = PathTools.pathToSourceFile(sourceRoot, FileReaderDemo.class);
        try (Reader reader = new BufferedReader(new FileReader(path))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
