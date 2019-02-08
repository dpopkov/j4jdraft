package ru.j4jdraft.io.common;

import java.io.File;

public class PathTools {
    public static String pathToSourceFile(String sourceRoot, Class<?> clazz) {
        return sourceRoot + File.separator
                + clazz.getName().replace(".", File.separator)
                + ".java";
    }
}
