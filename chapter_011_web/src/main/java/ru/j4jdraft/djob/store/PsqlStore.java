package ru.j4jdraft.djob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.model.Post;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class PsqlStore implements Store {
    private final BasicDataSource pool;

    private PsqlStore() {
        Properties props = loadProps();
        pool = initPool(props);
    }

    private Properties loadProps() {
        Properties props = new Properties();
        try (InputStream in = PsqlStore.class.getResourceAsStream("/db.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return props;
    }

    private BasicDataSource initPool(Properties props) {
        BasicDataSource pool = new BasicDataSource();
        try {
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(props.getProperty("jdbc.driver"));
        pool.setUrl(props.getProperty("jdbc.url"));
        pool.setUsername(props.getProperty("jdbc.username"));
        pool.setPassword(props.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
        return pool;
    }

    private static class Lazy {
        private static final PsqlStore INSTANCE = new PsqlStore();
    }

    public static PsqlStore getInstance() {
        return Lazy.INSTANCE;
    }

    @Override
    public Collection<Post> findAllPosts() {
        Collection<Post> posts = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM post");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                posts.add(new Post(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        Collection<Candidate> posts = new ArrayList<>();
        try (Connection conn = pool.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, image_id FROM candidate");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String imageId = rs.getString("image_id");
                posts.add(new Candidate(id, name, imageId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public void save(Post post) {
        try (Connection conn = pool.getConnection()) {
            if (post.getId() == 0) {
                create(conn, post);
            } else {
                update(conn, post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void create(Connection conn, Post post) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO post (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, post.getName());
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            post.setId(id);
        }
    }

    private void update(Connection conn, Post post) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE post SET name = ? WHERE id = ?");
        stmt.setString(1, post.getName());
        stmt.setInt(2, post.getId());
        stmt.executeUpdate();
    }

    @Override
    public void save(Candidate candidate) {
        try (Connection conn = pool.getConnection()) {
            if (candidate.getId() == 0) {
                create(conn, candidate);
            } else {
                update(conn, candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void create(Connection conn, Candidate candidate) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO candidate (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, candidate.getName());
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            candidate.setId(id);
        }
    }

    private void update(Connection conn, Candidate candidate) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE candidate SET name = ?, image_id = ? WHERE id = ?");
        stmt.setString(1, candidate.getName());
        stmt.setString(2, candidate.getPhotoId());
        stmt.setInt(3, candidate.getId());
        stmt.executeUpdate();
    }

    @Override
    public Post findPostById(int id) {
        Post post = null;
        try (Connection conn = pool.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM post WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                post = new Post(id, rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Candidate findCandidateById(int id) {
        Candidate candidate = null;
        try (Connection conn = pool.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT name, image_id FROM candidate WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                candidate = new Candidate(id, rs.getString("name"), rs.getString("image_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidate;
    }

    @Override
    public int nextPhotoId() {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO photo DEFAULT VALUES", Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
