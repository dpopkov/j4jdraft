package ru.j4jdraft.io.demos;

import java.io.*;

/**
 * Copies a file.
 * Arguments: source-filename destination-filename.
 */
public class CopyFileDemo {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: CopyFileDemo source destination");
            return;
        }
        try (InputStream input = new BufferedInputStream(new FileInputStream(args[0]));
             OutputStream output = new BufferedOutputStream(new FileOutputStream(args[1]))) {
            int ch;
            while ((ch = input.read()) != -1) {
                output.write(ch);
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
