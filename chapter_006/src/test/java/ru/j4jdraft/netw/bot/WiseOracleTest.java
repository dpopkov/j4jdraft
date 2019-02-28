package ru.j4jdraft.netw.bot;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WiseOracleTest {
    private WiseOracle oracle;

    @Before
    public void setup() {
        Map<String, String> data = Map.of("Java", "Java is used for web and mobile applications.");
        oracle = new WiseOracle(data);
    }

    @Test
    public void whenRequestContainsKeyWordThenResponseContainsKeyWord() {
        String question = "What is Java commonly used for?";
        String answer = oracle.reply(question);
        assertThat(answer, containsString("Java"));
    }

    @Test
    public void whenRequestContainsNoKeyWordsThenDontKnow() {
        String question = "When was Python invented?";
        String answer = oracle.reply(question);
        assertThat(answer, is("I don't know"));
    }
}
