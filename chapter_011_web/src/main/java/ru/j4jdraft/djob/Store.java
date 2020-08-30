package ru.j4jdraft.djob;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INSTANCE = new Store();
    private static final AtomicInteger NEXT_POST_ID = new AtomicInteger(4);
    private static final AtomicInteger NEXT_CANDIDATE_ID = new AtomicInteger(4);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", LocalDate.now()));
        posts.put(2, new Post(2, "Middle Java Job", LocalDate.now()));
        posts.put(3, new Post(3, "Senior Java Job", LocalDate.now()));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    public Collection<Post> findAllPosts() {
        return new ArrayList<>(posts.values());
    }

    public Collection<Candidate> findAllCandidates() {
        return new ArrayList<>(candidates.values());
    }

    public void save(Post post) {
        post.setId(NEXT_POST_ID.getAndIncrement());
        posts.put(post.getId(), post);
    }

    public void save(Candidate candidate) {
        candidate.setId(NEXT_CANDIDATE_ID.getAndIncrement());
        candidates.put(candidate.getId(), candidate);
    }
}
