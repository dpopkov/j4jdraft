package ru.j4jdraft.io.demos4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDemo {
    public static void main(String[] args) {
        String source = "Now is the time for all good men\n"
                + " to come to the aid of their country\n"
                + " and pay their due taxes.";
        try (Writer out1 = new BufferedWriter(new FileWriter("txt/file1.txt"));
             Writer out2 = new BufferedWriter(new FileWriter("txt/file2.txt"));
             Writer out3 = new BufferedWriter(new FileWriter("txt/file3.txt"))) {
            char[] buffer = new char[source.length()];
            source.getChars(0, source.length(), buffer, 0);
            for (int i = 0; i < buffer.length; i += 2) {
                out1.write(buffer[i]);
            }
            out2.write(buffer);
            out3.write(source, source.length() - source.length() / 4, source.length() / 4);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
