package ru.j4jdraft.io.demos;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Demonstrates usage of {@code PrintWriter} with various character sets.
 */
public class PrintWriterDemo {
    public static void main(String[] args) {
        List<Charset> charsets = List.of(
                StandardCharsets.US_ASCII,
                StandardCharsets.ISO_8859_1,
                StandardCharsets.UTF_8,
                StandardCharsets.UTF_16,
                Charset.forName("cp1251"),
                Charset.forName("cp866")    // cp866 is used by default in win10 cmd
        );
        charsets.forEach(cs -> {
            System.out.println("Output for charset " + cs + ":");
            PrintWriter writer = new PrintWriter(System.out, true, cs);
            writer.println("Это строка");
        });
        System.out.println("Output from System.out.println:");
        System.out.println("Это строка");
    }
}
