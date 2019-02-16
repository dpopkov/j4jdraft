package ru.j4jdraft.io.jimfs;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ForJimfsTestProbe {
    private FileSystem fileSystem;

    @Before
    public void setUp() {
        fileSystem = Jimfs.newFileSystem(Configuration.unix());
    }

    @Test
    public void testFileSize() throws IOException {
        Path p = fileSystem.getPath("test.txt");
        Files.write(p, "Java".getBytes());
        assertThat(p.toAbsolutePath().toString(), is("/work/test.txt"));
        assertThat(Files.exists(p), is(true));
        assertThat(new ForJimfsProbe().fileSize(p), is(4L));
        List<String> lines = Files.readAllLines(p);
        assertThat(lines.get(0), is("Java"));
    }
}
