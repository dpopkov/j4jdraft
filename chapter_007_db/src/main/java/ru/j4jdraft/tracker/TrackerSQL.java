package ru.j4jdraft.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SqlResolve", "SqlWithoutWhere"})
public class TrackerSQL implements ITracker, AutoCloseable {
    static final String ADD_ITEM = "insert into item (name, description, created) values (?, ?, ?)";
    static final String REPLACE_ITEM = "update item set name = ?, description = ?, created = ? WHERE id = ?";
    static final String DELETE_ITEM = "delete from item where id = ?";
    static final String FIND_ALL = "select id, name, description, created from item";
    static final String FIND_BY_NAME = "select id, name, description, created from item where name = ?";
    static final String FIND_BY_ID = "select id, name, description, created from item where id = ?";
    static final String DELETE_ALL = "delete from item";

    private static final Logger log = LoggerFactory.getLogger(TrackerSQL.class);

    private final Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                item.setId(Integer.toString(id));
                return item;
            } else {
                throw new SQLException("Could not get generated key");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement ps = connection.prepareStatement(REPLACE_ITEM)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.setInt(4, Integer.parseInt(id));
            int numRows = ps.executeUpdate();
            return numRows == 1;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ITEM)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Item> findAll() {
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            return queryItems(ps);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<Item> findByName(String name) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, name);
            return queryItems(ps);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public Item findById(String id) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, Integer.parseInt(id));
            List<Item> items = queryItems(ps);
            if (items.size() == 1) {
                return items.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private List<Item> queryItems(PreparedStatement ps) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameValue = rs.getString("name");
            String desc = rs.getString("description");
            long created = rs.getLong("created");
            Item item = new Item(nameValue, desc, created);
            item.setId(Integer.toString(id));
            items.add(item);
        }
        return items;
    }

    public void deleteAll() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(DELETE_ALL);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
