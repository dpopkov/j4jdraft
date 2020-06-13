package ru.j4jdraft.jmm.cach;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenDoesNotHaveCachedValueThenReceivesValueFromProvider() {
        Cache<String, Long> cache = new Cache<>(Long::valueOf);
        Long result = cache.get("123");
        assertThat(result, is(123L));
        cache.setProvider((s) -> Long.parseLong(s) * 2);
        result = cache.get("321");
        assertThat(result, is(642L));
    }

    @Test
    public void whenHasCachedValueThenReturnsCachedInstance() {

        Cache<String, Dummy> cache = new Cache<>((s) -> new Dummy());
        // todo: implement test
    }

    private static class Dummy {}
}
