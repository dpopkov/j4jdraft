package ru.j4jdraft.io.demos3;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {
    public static void main(String[] args) {
        String s = "This is a &copy; copyright symbol "
                + "but this is &copy not.\n";
        byte[] buf = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        boolean marked = false;
        try (BufferedInputStream stream = new BufferedInputStream(in)) {
            int ch;
            while ((ch = stream.read()) != -1) {
                switch (ch) {
                    case '&':
                        if (!marked) {
                            stream.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        } else {
                            System.out.print((char) ch);
                        }
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            stream.reset();
                            System.out.print("&");
                        } else {
                            System.out.print((char) ch);
                        }
                        break;
                    default:
                        if (!marked) {
                            System.out.print((char) ch);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
