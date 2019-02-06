package ru.j4jdraft.io.demos;

import java.io.*;

/**
 * Displays a text file.
 * To use this program, specify the name of the file
 * that you want to see as command line argument.
 */
public class ShowFileDemo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: ShowFileDemo filename");
            return;
        }
        try (InputStream input = new BufferedInputStream(new FileInputStream(args[0]))) {
            do {
                int ch = input.read();
                if (ch == -1) {
                    break;
                } else {
                    System.out.print((char) ch);
                }
            } while(true);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}
