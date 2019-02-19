package ru.j4jdraft.io.zip;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser of command line arguments.
 */
public class Args {
    private String dir;
    private List<String> extList;
    private String out;

    public Args(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        extList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String token = args[i];
            if ("-d".equals(token)) {
                dir = args[++i];
            } else if ("-e".equals(token)) {
                int j = i + 1;
                while (j < args.length && !(token = args[j]).startsWith("-")) {
                    extList.add(token);
                    j++;
                }
                i = j - 1;
            } else if ("-o".equals(token)) {
                out = args[++i];
            }
        }
        if (dir == null) {
            throw new IllegalArgumentException("args should contain '-d' 'directory' arguments");
        }
        if (out == null) {
            out = dir + ".zip";
        }
    }

    public String directory() {
        return dir;
    }

    public List<String> exclude() {
        return extList;
    }

    public String output() {
        return out;
    }
}
