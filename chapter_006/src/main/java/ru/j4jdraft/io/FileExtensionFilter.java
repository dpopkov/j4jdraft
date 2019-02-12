package ru.j4jdraft.io;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileExtensionFilter implements FileFilter {
    private final Set<String> extSet;

    public FileExtensionFilter(List<String> extensions) {
        this.extSet = new HashSet<>(extensions);
    }

    @Override
    public boolean accept(File file) {
        return file.isFile() && extSet.contains(getExtension(file.getName()));
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
