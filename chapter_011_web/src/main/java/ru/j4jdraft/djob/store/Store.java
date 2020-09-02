package ru.j4jdraft.djob.store;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;

import java.util.Collection;

public interface Store {

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate candidate);

    Post findPostById(int id);

    Candidate findCandidateById(int id);
}
