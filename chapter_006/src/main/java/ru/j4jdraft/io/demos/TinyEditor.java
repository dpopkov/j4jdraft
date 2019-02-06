package ru.j4jdraft.io.demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TinyEditor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int capacity = 100;
        int count = 0;
        String[] lines = new String[capacity];
        System.out.println("Enter lines of text or 'stop'");
        for (int i = 0; i < capacity; i++) {
            lines[i] = reader.readLine();
            if ("stop".equals(lines[i])) {
                break;
            } else {
                count++;
            }
        }
        System.out.println("\nHere is your file:");
        for (int i = 0; i < count; i++) {
            System.out.println(lines[i]);
        }
    }
}
