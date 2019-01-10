package ru.j4jdraft.collections.stats;

import java.util.List;
import java.util.Objects;

public class Analyzer {

    public Info diff(List<User> previous, List<User> current) {
        int countAdded = 0;
        int countDeleted = 0;
        int countChanged = 0;
        for (User user : current) {
            boolean found = false;
            for (User prev : previous) {
                Equality eq = User.equals(prev, user);
                if (eq == Equality.FULL) {
                    found = true;
                    break;
                } else if (eq == Equality.BY_ID) {
                    found = true;
                    countChanged++;
                    break;
                }
            }
            if (!found) {
                countAdded++;
            }
        }
        for (User prev : previous) {
            boolean deleted = true;
            for (User user : current) {
                Equality eq = User.equals(prev, user);
                if (eq == Equality.BY_ID || eq == Equality.FULL) {
                    deleted = false;
                    break;
                }
            }
            if (deleted) {
                countDeleted++;
            }
        }
        return new Info(countAdded, countChanged, countDeleted);
    }

    public enum Equality {
        NO,
        BY_ID,
        FULL
    }

    public static class User {
        public final int id;
        public final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static Equality equals(User u1, User u2) {
            if (u1.id == u2.id) {
                if (Objects.equals(u1.name, u2.name)) {
                    return Equality.FULL;
                } else {
                    return Equality.BY_ID;
                }
            }
            return Equality.NO;
        }
    }

    public static class Info {
        public final int added;
        public final int changed;
        public final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}
