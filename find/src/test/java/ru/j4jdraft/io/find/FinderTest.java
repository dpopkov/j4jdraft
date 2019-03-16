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
        } else {
            testDir = Files.createTempDirectory("test");
        }
    }

    private final Finder finder = new Finder(testDir, 5);

    @Test
    public void whenFindByFullNameThenFindsTheFile() throws IOException {
        Files.createTempFile(testDir, "test1", ".tmp");
        Path file2 = Files.createTempFile(testDir, "test2", ".tmp");
        List<Path> result = finder.find(file2.getFileName().toString(), SearchBy.FULL);
        assertThat(result, is(List.of(file2)));
    }

    @Test
    public void whenFindByMaskThenFindsCorrectGroupOfFiles() throws IOException {
        Path file1 = Files.createTempFile(testDir, "test11", ".xml");
        Files.createTempFile(testDir, "test22", ".tmp");
        Path file3 = Files.createTempFile(testDir, "test33", ".xml");
        List<Path> result = finder.find("*.xml", SearchBy.MASK);
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(file1, file3));
    }

    @Test
    public void whenFindByRegexThenFindsCorrectGroupOfFiles() throws IOException {
        Path file1 = Files.createTempFile(testDir, "reg1ex", null);
        Files.createTempFile(testDir, "reg2ex", ".tmp");
        Path file3 = Files.createTempFile(testDir, "reg3ex", null);
        List<Path> result = finder.find("^reg[13]ex.+", SearchBy.REGEX);
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(file1, file3));
    }

    @Test
    public void whenFindInNestedFoldersThenFindsCorrectGroupOfFiles() throws IOException {
        Path dir1 = Files.createTempDirectory(testDir, "dir1");
        Path file1 = Files.createTempFile(dir1, "nested", ".txt");
        Path dir12 = Files.createTempDirectory(dir1, "dir12");
        Files.createTempFile(dir12, "irrelevant", ".txt");
        Path file3 = Files.createTempFile(dir12, "nested", ".txt");
        List<Path> result = finder.find("nested*.txt", SearchBy.MASK);
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(file1, file3));
    }

    @Test
    public void whenFindInNestedFoldersByFullNameThenFindsCorrectGroupOfFiles() throws IOException {
        final String fileName = "test42.txt";
        Path file0 = Files.createFile(testDir.resolve(fileName));
        Files.createFile(testDir.resolve("irrelevant"));
        Path dir1 = Files.createTempDirectory(testDir, "dir1");
        Path file1 = Files.createFile(dir1.resolve(fileName));
        Path dir12 = Files.createTempDirectory(dir1, "dir12");
        Path file3 = Files.createFile(dir12.resolve(fileName));
        List<Path> result = finder.find(fileName, SearchBy.FULL);
        assertThat(result.size(), is(3));
        assertThat(result, hasItems(file0, file1, file3));
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
