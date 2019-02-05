package ru.j4jdraft.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class InputStreamCheckerTest {
    @Test
    public void whenEmptyStreamThenFalse() {
        InputStream in = new ByteArrayInputStream(new byte[0]);
        boolean even = new InputStreamChecker().isEvenNumber(in);
        assertThat(even, is(false));
    }

    @Test
    public void whenStreamContainsEvenNumberThenTrue() {
        InputStream in = new ByteArrayInputStream(new byte[] {8});
        boolean even = new InputStreamChecker().isEvenNumber(in);
        assertThat(even, is(true));
    }

    @Test
    public void whenStreamContainsOddNumberThenFalse() {
        InputStream in = new ByteArrayInputStream(new byte[] {11, 8});
        boolean even = new InputStreamChecker().isEvenNumber(in);
        assertThat(even, is(false));
    }
}
