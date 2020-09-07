package ru.j4jdraft.djob.store;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;
import ru.j4jdraft.djob.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StoreStub implements Store {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final Map<Integer, Candidate> candidates = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public void save(Post post) {
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return users.get(email);
    }

    @Override
    public int nextPhotoId() {
        return 0;
    }
}
