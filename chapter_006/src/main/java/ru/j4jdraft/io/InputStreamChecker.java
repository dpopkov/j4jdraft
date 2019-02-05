package ru.j4jdraft.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamChecker {

    /**
     * Checks whether the specified input stream contains an even number.
     * @param in input stream
     * @return true if the stream contains even number, otherwise false
     */
    public boolean isEvenNumber(InputStream in) {
        try (InputStream checked = new BufferedInputStream(in)) {
            if (checked.available() > 0) {
                int number = checked.read();
                return number % 2 == 0;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
