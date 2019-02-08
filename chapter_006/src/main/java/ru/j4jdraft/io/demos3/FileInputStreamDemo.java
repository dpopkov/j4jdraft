package ru.j4jdraft.io.demos3;

import ru.j4jdraft.io.common.PathTools;

import java.io.*;
import java.nio.file.Paths;

/**
 * Demonstrates class {@code FileInputStream}.
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        String sourceRoot = Paths.get("chapter_006", "src", "main", "java").toString();
        String path = PathTools.pathToSourceFile(sourceRoot, FileInputStreamDemo.class);
        try (InputStream input = new BufferedInputStream(new FileInputStream(path))) {
            int size;
            System.out.println("Total Available Bytes: " + (size = input.available()));
            int n = size / 40;
            System.out.println("First " + n + " bytes of the file on read() at a time");
            for (int i = 0; i < n; i++) {
                System.out.print((char) input.read());
            }
            System.out.println("\nStill Available: " + input.available());
            System.out.println("Reading the next " + n + " with one read(b[])");
            byte[] buffer = new byte[n];
            if (input.read(buffer) != n) {
                System.err.println("couldn't read " + n + " bytes");
            }
            System.out.println(new String(buffer, 0, n));
            System.out.println("\nStill Available: " + (size = input.available()));
            System.out.println("Skipping half of remaining bytes with skip()");
            long skipped = input.skip(size / 2);
            System.out.println("skipped = " + skipped);
            System.out.println("\nStill Available: " + input.available());

            int n2 = n / 2;
            System.out.println("Reading " + n2 + " into the end of array");
            if (input.read(buffer, n2, n2) != n2) {
                System.out.println("Couldn't read " + n2 + " bytes");
            }
            System.out.println(new String(buffer, 0, buffer.length));
            System.out.println("\nStill Available: " + input.available());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
