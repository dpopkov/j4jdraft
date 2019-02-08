package ru.j4jdraft.io.demos3;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.List;

/**
 * Demonstrates {@code SequenceInputStream} usage to read bytes form a sequence of files.
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) {
        var filenames = List.of("txt/file1.txt", "txt/file2.txt", "txt/file3.txt", "txt/test.txt");
        var streams = new InputStreamEnumerator(filenames);
        try (InputStream input = new SequenceInputStream(streams)) {
            int ch;
            while ((ch = input.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
