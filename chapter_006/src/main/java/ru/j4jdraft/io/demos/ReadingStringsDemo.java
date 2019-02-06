package ru.j4jdraft.io.demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingStringsDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'stop' to quit");
        String str;
        do {
            str = reader.readLine();
            System.out.println(str);
        } while (!str.equals("stop"));
    }
}
