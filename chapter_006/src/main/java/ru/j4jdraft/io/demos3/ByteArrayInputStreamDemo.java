package ru.j4jdraft.io.demos3;

import java.io.ByteArrayInputStream;

/**
 * Demonstrates {@code ByteArrayInputStream}.
 */
public class ByteArrayInputStreamDemo {
    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
        String tmp = "abcdefghijklmnopqrstuvwxyz";
        byte[] b = tmp.getBytes();
        ByteArrayInputStream in1 = new ByteArrayInputStream(b);
        ByteArrayInputStream in2 = new ByteArrayInputStream(b, 1, 3);
        System.out.println((char)in1.read());
        int ch;
        while ((ch = in2.read()) != -1) {
            System.out.println((char)ch);
        }
    }
}
