package ru.j4jdraft.mt.synchr;

import java.io.*;

/**
 * Исходный вариант класса.
 * Содержит ошибки: незакрытие I/O потоков, отсутствие синхронизации,
 * конкатенация в цикле, несоответсвие названий методов тому что они делают.
 */
@SuppressWarnings("StringConcatenationInLoop")
public class ParseFileInitial {
    private File file;

    public synchronized File getFile() {
        return file;
    }

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        return output;
    }

    public void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
