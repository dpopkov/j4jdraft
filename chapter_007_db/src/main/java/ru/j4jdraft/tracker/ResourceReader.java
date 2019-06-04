package ru.j4jdraft.tracker;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResourceReader {
    public static String read(String resourceName) throws IOException {
        InputStream in = ResourceReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (in != null) {
            try (Scanner scanner = new Scanner(new BufferedInputStream(in))) {
                return scanner.useDelimiter("\\A").next();
            }
        } else {
            throw new IOException("Cannot open resource: " + resourceName);
        }
    }

    public static List<String> readSqlStatements(String resourceName) throws IOException {
        InputStream in = ResourceReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (in != null) {
            List<String> statements = new ArrayList<>();
            try (Scanner scanner = new Scanner(new BufferedInputStream(in))) {
                scanner.useDelimiter(";\\s*");
                while (scanner.hasNext()) {
                    statements.add(scanner.next());
                }
                return statements;
            }
        } else {
            throw new IOException("Cannot open resource: " + resourceName);
        }
    }

    public static void main(String[] args) throws IOException {
        String name = "sql/createTestDb1.sql";
        List<String> sql = readSqlStatements(name);
        for (String s : sql) {
            System.out.println("\nSQL:");
            System.out.println(s);
        }
    }
}
