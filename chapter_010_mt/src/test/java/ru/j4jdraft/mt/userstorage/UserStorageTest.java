package ru.j4jdraft.mt.userstorage;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenUpdateThenChangesAmount() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        User user1 = new User(1, 100);
        user1.setAmount(300);
        var ok = storage.update(user1);
        assertTrue(ok);
        User updated = storage.get(1);
        assertThat(updated.getAmount(), is(300));
        ok = storage.update(new User(2, 0));
        assertFalse(ok);
    }

    @Test
    public void whenDeleteThenUserIsDeleted() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        boolean ok = storage.delete(new User(1, 0));
        assertTrue(ok);
        User found = storage.get(1);
        assertNull(found);
        ok = storage.delete(new User(2, 0));
        assertFalse(ok);
    }

    @Test
    public void whenTransferThenMovesAmountBetweenUsers() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        assertTrue(storage.add(user1));
        assertTrue(storage.add(user2));
        boolean ok = storage.transfer(1, 2, 50);
        assertTrue(ok);
        assertThat(user1.getAmount(), is(50));
        assertThat(user2.getAmount(), is(250));
    }

    @Test
    public void whenManyThreadsTransferThenAllTransfersAreCorrect() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final int numTransfers = 100;
        Runnable from1to2 = () -> {
            for (int j = 0; j < numTransfers; j++) {
                storage.transfer(1, 2, 10);
            }
        };
        Runnable from2to1 = () -> {
            for (int j = 0; j < numTransfers; j++) {
                storage.transfer(2, 1, 10);
            }
        };
        for (int i = 0; i < 5; i++) {
            exec.submit(from1to2);
            exec.submit(from2to1);
        }
        exec.shutdown();
        assertTrue(exec.awaitTermination(200, TimeUnit.MILLISECONDS));
        assertThat(user1.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }
}
