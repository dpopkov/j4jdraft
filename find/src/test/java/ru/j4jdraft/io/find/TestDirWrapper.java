package ru.j4jdraft.io.find;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TestDirWrapper {
    private Path path;

    public TestDirWrapper() throws IOException {
        if (TestingSettings.testDirProvider().equals("Jimfs")) {
            path = Jimfs.newFileSystem(Configuration.unix()).getPath("testDir");
            Files.createDirectory(path);
        } else {
            path = Files.createTempDirectory("test");
        }
    }

    public Path getPath() {
        return path;
    }

    public void clean() throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
