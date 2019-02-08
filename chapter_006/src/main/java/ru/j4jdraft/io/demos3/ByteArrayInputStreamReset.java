package ru.j4jdraft.io.demos3;

import java.io.ByteArrayInputStream;

/**
 * Demonstrates reset of {@code ByteArrayInputStream} to the start of the stream.
 */
public class ByteArrayInputStreamReset {
    public static void main(String[] args) {
        String tmp = "abc";
        byte[] b = tmp.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        for (int i = 0; i < 2; i++) {
            int ch;
            while ((ch = in.read()) != -1) {
                if (i == 0) {
                    System.out.print((char) ch);
                } else {
                    System.out.print(Character.toUpperCase((char) ch));
                }
            }
            System.out.println();
            in.reset();
        }
    }
}
