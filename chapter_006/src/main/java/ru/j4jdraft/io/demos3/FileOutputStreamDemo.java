package ru.j4jdraft.io.demos3;

import java.io.*;

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        String source = "Now is the time for all good men\n"
                + " to come to the aid of their country\n"
                + " and pay their due taxes.";
        byte[] buffer = source.getBytes();
        try (OutputStream out1 = new BufferedOutputStream(new FileOutputStream(makeName("file1")));
             OutputStream out2 = new BufferedOutputStream(new FileOutputStream(makeName("file2")));
             OutputStream out3 = new BufferedOutputStream(new FileOutputStream(makeName("file3")))) {
            for (int i = 0; i < buffer.length; i += 2) {
                out1.write(buffer[i]);
            }
            out2.write(buffer);
            out3.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);
        } catch (IOException e) {
            System.err.println("I/O error: " + e);
        }
    }

    private static String makeName(String name) {
        return "txt" + File.separator + name + ".txt";
    }
}
