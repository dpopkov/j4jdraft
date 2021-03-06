package ru.j4jdraft.io;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileExtensionFilter implements FileFilter {
    private Set<String> extSet;

    public FileExtensionFilter(List<String> extensions) {
        if (extensions != null) {
            this.extSet = new HashSet<>(extensions);
        }
    }

    @Override
    public boolean accept(File file) {
        return extSet == null || extSet.contains(getExtension(file.getName()));
    }

    public boolean accept(Path path) {
        return extSet == null || extSet.contains(getExtension(path.getFileName().toString()));
    }

    public boolean reject(Path path) {
        return !accept(path);
    }

    private static String getExtension(String filename) {
        String ext = "";
        int pos = filename.lastIndexOf('.');
        if (pos >= 0) {
            ext = filename.substring(pos + 1);
        }
        return ext;
    }
}
