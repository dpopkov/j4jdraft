package ru.j4jdraft.mt.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private static final Logger LOG = LoggerFactory.getLogger(UserStorage.class);

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        var previous = users.putIfAbsent(user.getId(), user);
        return previous == null;
    }

    public synchronized User get(int id) {
        var found = users.get(id);
        return found == null ? null : new User(found);
    }

    public synchronized boolean update(User user) {
        User found = users.get(user.getId());
        if (found == null) {
            return false;
        }
        found.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        User found = users.remove(user.getId());
        return found != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);
        if (from == null || to == null || from.getAmount() < amount) {
            return false;
        }
        LOG.trace("Transfer {} from {} to {}", amount, from, to);
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        LOG.trace("After transfer: from={}, to={}", from, to);
        return true;
    }
}
