package ru.j4jdraft.io.demos2;

import java.io.File;

/**
 * Demonstrates using directories.
 */
public class DirList {
    public static void main(String[] args) {
        String dirname = "chapter_006";
        if (args.length == 1) {
            dirname = args[0];
        }
        File dir = new File(dirname);
        if (dir.isDirectory()) {
            System.out.println("Directory of " + dirname);
            String[] items = dir.list();
            if (items != null) {
                for (String s : items) {
                    File f = new File(dir, s);
                    if (f.isDirectory()) {
                        System.out.println(s + " is a directory");
                    } else {
                        System.out.println(s + " is a file");
                    }
                }
            }
        } else {
            System.out.println(dirname + " is not a directory");
        }
    }
}
