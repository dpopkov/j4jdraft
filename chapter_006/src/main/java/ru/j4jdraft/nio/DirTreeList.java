package ru.j4jdraft.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirTreeList {
    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            System.out.println(file);
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) {
        String dirname = ".";
        Path dirPath = Paths.get(dirname).normalize();
        System.out.println("Directory tree starting with " + dirPath.toAbsolutePath());
        try {
            Files.walkFileTree(dirPath, new MyFileVisitor());
        } catch (IOException e) {
            System.out.println("I/O error");
        }
    }
}
