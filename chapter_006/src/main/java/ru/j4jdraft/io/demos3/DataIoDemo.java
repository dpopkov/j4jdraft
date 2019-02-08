package ru.j4jdraft.io.demos3;

import java.io.*;

/**
 * Demonstrates {@code DataInputStream} and {@code DataOutputStream}.
 */
public class DataIoDemo {
    public static void main(String[] args) {
        String filename = "txt/test.dat";
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeDouble(98.6);
            out.writeInt(1000);
            out.writeBoolean(true);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            double d = in.readDouble();
            int n = in.readInt();
            boolean b = in.readBoolean();
            System.out.printf("Here are the values: %f %d %s%n", d, n, b);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
