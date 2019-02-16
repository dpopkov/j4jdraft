package ru.j4jdraft.io.jimfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Dummy class for probing Jimfs.
 */
public class ForJimfsProbe {
    public long fileSize(Path path) throws IOException {
        return Files.size(path);
    }
}
