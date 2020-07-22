package ru.j4jdraft.mt.resources;

import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@ThreadSafe
public class UserCache {
    private final AtomicInteger lastId = new AtomicInteger();
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    public void add(User user) {
        users.put(lastId.incrementAndGet(), user);
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        return users.values()
                .stream()
                .map(u -> User.of(u.getName()))
                .collect(Collectors.toList());
    }
}
