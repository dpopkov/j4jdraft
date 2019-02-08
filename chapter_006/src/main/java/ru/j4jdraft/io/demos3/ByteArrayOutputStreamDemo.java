package ru.j4jdraft.io.demos3;

import java.io.*;

public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        String s = "This should end up in the array";
        byte[] buffer = s.getBytes();
        try {
            stream.write(buffer);
        } catch (IOException e) {
            System.out.println("Error writing to array stream");
            return;
        }
        System.out.println("Array stream as a string");
        System.out.println(stream.toString());
        System.out.println("\nInto byte array");
        byte[] b = stream.toByteArray();
        for (byte ch : b) {
            System.out.print((char) ch);
        }
        System.out.println("\n\nTo an OutputStream");
        try (OutputStream file = new BufferedOutputStream(new FileOutputStream("txt/test.txt"))) {
            stream.writeTo(file);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            return;
        }
        System.out.println("\nDoing a reset");
        stream.reset();
        for(int i = 0; i < 3; i++) {
            stream.write('A');
        }
        System.out.println(stream.toString());
    }
}
