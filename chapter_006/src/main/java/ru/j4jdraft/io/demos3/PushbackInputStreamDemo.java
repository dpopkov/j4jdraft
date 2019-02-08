package ru.j4jdraft.io.demos3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Demonstration of {@code PushbackInputStream} usage to replace characters.
 */
public class PushbackInputStreamDemo {
    public static void main(String[] args) {
        String s = "if (a == 4) a = 0;\n";
        byte[] buf = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int ch;
        try (PushbackInputStream stream = new PushbackInputStream(in)) {
            while ((ch = stream.read()) != -1) {
                if (ch == '=') {
                    int ch2 = stream.read();
                    if (ch2 == '=') {
                        System.out.print(".eq.");
                    } else {
                        stream.unread(ch2);
                        System.out.print("<-");
                    }
                } else {
                    System.out.print((char) ch);
                }
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
