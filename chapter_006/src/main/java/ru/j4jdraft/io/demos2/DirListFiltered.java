package ru.j4jdraft.io.demos2;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Demonstrates listing filtered directory items.
 */
public class DirListFiltered {
    public static void main(String[] args) {
        String dirname = ".";
        File dir = new File(dirname);
        FilenameFilter xmlOnly = new FilenameFilterByExtension(".xml");
        String[] items = dir.list(xmlOnly);
        if (items != null) {
            for (String s : items) {
                System.out.println(s);
            }
        }
    }
}
