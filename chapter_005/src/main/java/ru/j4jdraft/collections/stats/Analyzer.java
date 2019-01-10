package ru.j4jdraft.collections.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analyzer {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> idUsers = new HashMap<>();
        current.forEach(user -> idUsers.put(user.id, user));
        previous.forEach(prev -> {
            User found = idUsers.get(prev.id);
            if (found == null) {
                info.deleted++;
            } else if(!Objects.equals(found.name, prev.name)) {
                info.changed++;
            }
        });
        info.added = current.size() - (previous.size() - info.deleted);
        return info;
    }

    public static class User {
        public final int id;
        public final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
