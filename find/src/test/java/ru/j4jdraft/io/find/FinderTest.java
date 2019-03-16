package ru.j4jdraft.io.find;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class FinderTest {
    private static Path testDir;

    @BeforeClass
    public static void setupTestDir() throws IOException {
        if (TestingSettings.testDirProvider().equals("Jimfs")) {
            testDir = Jimfs.newFileSystem(Configuration.unix()).getPath("testDir");
            Files.createDirectory(testDir);
            System.out.println("Using Jimfs");
        } else {
            testDir = Files.createTempDirectory("test");
            System.out.println("Using default FS");
        }
        System.out.println("Test directory: " + testDir.toRealPath());
    }

    @Test
    public void whenFindByFullNameThenFindsTheFile() throws IOException {
        Files.createTempFile(testDir, "test1", ".tmp");
        Path file2 = Files.createTempFile(testDir, "test2", ".tmp");
        Finder finder = new Finder(testDir);
        List<Path> result = finder.find(file2.getFileName().toString(), SearchBy.FULL);
        assertThat(result, is(List.of(file2)));
    }

    @Test
    public void whenFindByMaskThenFindsGroupOfFiles() throws IOException {
        Path file1 = Files.createTempFile(testDir, "test11", ".xml");
        Files.createTempFile(testDir, "test22", ".tmp");
        Path file3 = Files.createTempFile(testDir, "test33", ".xml");
        Finder finder = new Finder(testDir);
        List<Path> result = finder.find("*.xml", SearchBy.MASK);
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(file1, file3));
    }

    @AfterClass
    public static void cleanTestDir() throws IOException {
        Files.walkFileTree(testDir, new SimpleFileVisitor<>() {
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