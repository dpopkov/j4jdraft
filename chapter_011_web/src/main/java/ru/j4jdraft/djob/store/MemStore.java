package ru.j4jdraft.djob.store;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INSTANCE = new MemStore();
    private static final AtomicInteger NEXT_POST_ID = new AtomicInteger(4);
    private static final AtomicInteger NEXT_CANDIDATE_ID = new AtomicInteger(4);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public static MemStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return new ArrayList<>(candidates.values());
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(NEXT_POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(NEXT_CANDIDATE_ID.getAndIncrement());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }
}
