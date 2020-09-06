package ru.j4jdraft.djob.store;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;
import ru.j4jdraft.djob.model.User;

import java.util.Collection;

public interface Store {

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    User findUserByEmail(String email);

    int nextPhotoId();
}
