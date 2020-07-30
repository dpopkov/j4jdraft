package ru.j4jdraft.mt.cas;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CasStackTest {

    @Test
    public void whenPushAndPopThenProvidesLastInFirstOut() {
        CasStack<Integer> stack = new CasStack<>();
        stack.push(11);
        stack.push(22);
        assertFalse(stack.isEmpty());
        assertThat(stack.pop(), is(22));
        assertThat(stack.pop(), is(11));
        assertTrue(stack.isEmpty());
    }

    @Test
    public void whenUsedInThreadsThenWorksCorrect() throws InterruptedException {
        CasStack<Integer> stack = new CasStack<>();
        final int taskSize = 100;
        Runnable loadingTask = () -> {
            for (int i = 0; i < taskSize; i++) {
                stack.push(i);
                stack.pop();
                stack.push(i);
            }
        };
        List<Thread> threads = List.of(new Thread(loadingTask),
                new Thread(loadingTask), new Thread(loadingTask)
        );
        threads.forEach(Thread::start);
        for (Thread t : threads) {
            t.join();
        }
        int length = lengthOf(stack);
        assertThat(length, is(taskSize * 3));
    }

    private static int lengthOf(CasStack<Integer> stack) {
        int count = 0;
        while (!stack.isEmpty()) {
            stack.pop();
            count++;
        }
        return count;
    }
}
