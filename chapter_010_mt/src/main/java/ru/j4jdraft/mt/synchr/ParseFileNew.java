package ru.j4jdraft.mt.synchr;

import java.io.*;

/**
 * Название ParseFile не соответствует тому что делает файл.
 * Здесь нет парсинга, только чтение и сохранение текста.
 */
public class ParseFileNew {
    private final File file;

    /**
     * Constructs and initializes the instance with the specified file.
     * @param file target file
     */
    public ParseFileNew(File file) {
        this.file = file;
    }

    /** Returns the file. */
    public File getFile() {
        return file;
    }

    /** Reads the content of the file. */
    public synchronized String readContent() throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int n;
            while ((n = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, n);
            }
        }
        return sb.toString();
    }

    /** Reads the content of the file where all non-ASCII bytes are skipped. */
    public synchronized String readContentWithoutUnicode() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (data < 0x80) {
                    sb.append((char) data);
                }
            }
        }
        return sb.toString();
    }

    /** Writes the specified content to the file. */
    public synchronized void saveContent(String content) throws IOException {
        try (Writer out = new BufferedWriter(new FileWriter(file))) {
            out.write(content);
        }
    }
}
