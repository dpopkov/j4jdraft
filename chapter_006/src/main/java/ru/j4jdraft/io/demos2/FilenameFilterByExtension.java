package ru.j4jdraft.io.demos2;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterByExtension implements FilenameFilter {
    private final String extension;

    public FilenameFilterByExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
