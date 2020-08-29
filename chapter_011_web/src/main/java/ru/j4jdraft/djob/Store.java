package ru.j4jdraft.djob;

import ru.j4jdraft.djob.model.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    private static final Store INSTANCE = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", LocalDate.now()));
        posts.put(2, new Post(2, "Middle Java Job", LocalDate.now()));
        posts.put(3, new Post(3, "Senior Java Job", LocalDate.now()));
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    public Collection<Post> findAll() {
        return new ArrayList<>(posts.values());
    }
}
