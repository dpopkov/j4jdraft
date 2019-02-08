package ru.j4jdraft.io.demos3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implements {@code Enumeration} of input streams in order to use it with {@code SequenceInputStream}.
 */
public class InputStreamEnumerator implements Enumeration<InputStream> {
    private Iterator<String> namesIterator;

    InputStreamEnumerator(List<String> filenames) {
        namesIterator = filenames.iterator();
    }

    @Override
    public boolean hasMoreElements() {
        return namesIterator.hasNext();
    }

    @Override
    public InputStream nextElement() {
        String name = namesIterator.next();
        try {
            return new BufferedInputStream(new FileInputStream(name));
        } catch (FileNotFoundException e) {
            throw new NoSuchElementException("File " + name + " not found");
        }
    }
}
