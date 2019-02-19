package ru.j4jdraft.io.zip;

import java.io.IOException;
import java.nio.file.Path;

public class Pack {
    public static void main(String[] args) {
        Args arguments = new Args(args);
        try {
            Path result = new FolderArchiver().compress(
                    arguments.directory(), arguments.output(), arguments.exclude());
            System.out.println(result.toAbsolutePath() + " archive created");
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
