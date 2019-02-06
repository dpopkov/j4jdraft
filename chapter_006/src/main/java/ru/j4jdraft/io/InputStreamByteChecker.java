package ru.j4jdraft.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamByteChecker {
    /**
     * Checks whether the specified input stream contains an even byte.
     * @param in input stream
     * @return true if the stream contains even byte, otherwise false
     */
    public boolean isEvenByte(InputStream in) throws IOException {
        try (InputStream checked = new BufferedInputStream(in)) {
            if (checked.available() > 0) {
                int byteValue = checked.read();
                return byteValue % 2 == 0;
            }
            return false;
        }
    }
}
