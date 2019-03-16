package ru.j4jdraft.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class NioGlobUsage {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java NioGlobUsage path glob");
            return;
        }
        String path = args[0]; //"D:/";
        String glob = "glob:" + args[1]; //glob:**/*.zip";
        match(glob, path);
    }

    public static void match(String glob, String location) throws IOException {
        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                if (pathMatcher.matches(path.getFileName())) {
                    System.out.println(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
