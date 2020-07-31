package ru.j4jdraft.mt.nonblockingcache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NonBlockingCacheTest {

    @Test
    public void whenUpdateTwoModelsInSuccessionThenUpdatesBothAndIncrementsVersion() {
        NonBlockingCache cache = new NonBlockingCache();
        Base original = new Base(11);
        cache.add(original);
        Base first = cache.getBy(11);
        cache.update(first);
        Base second = cache.getBy(11);
        cache.update(second);
        int expectedVersion = 2;
        assertThat(cache.getBy(11).getVersion(), is(expectedVersion));
    }

    @Test(expected = ru.j4jdraft.mt.nonblockingcache.OptimisticException.class)
    public void whenUpdateTwoModelsInParallelThenThrowsException() {
        NonBlockingCache cache = new NonBlockingCache();
        Base original = new Base(11);
        cache.add(original);
        Base first = new Base(cache.getBy(11));
        Base second = new Base(cache.getBy(11));
        cache.update(first);
        cache.update(second);
    }

    @Test(expected = IllegalStateException.class)
    public void whenUpdateAbsentModelThenThrowsException() {
        NonBlockingCache cache = new NonBlockingCache();
        Base original = new Base(11);
        cache.add(original);
        cache.delete(original);
        cache.update(original);
    }

    @Test
    public void whenUpdateTwoModelsChangedInDifferentThreadsThenThrowException()
            throws InterruptedException {
        NonBlockingCache cache = new NonBlockingCache();
        Base original = new Base(11);
        cache.add(original);
        Base first = new Base(original);
        cache.update(first);
        AtomicReference<Exception> exceptionRef = new AtomicReference<>();
        Thread t = new Thread(() -> {
            Base second = new Base(original);
            try {
                cache.update(second);
            } catch (Exception e) {
                exceptionRef.set(e);
            }
        });
        t.start();
        t.join();
        assertThat(cache.getBy(11).getVersion(), is(1));
        assertThat(exceptionRef.get().getClass().getSimpleName(), is("OptimisticException"));
    }
}
