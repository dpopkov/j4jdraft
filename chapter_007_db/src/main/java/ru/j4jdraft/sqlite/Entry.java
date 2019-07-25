package ru.j4jdraft.sqlite;

public class Entry {
    private final int field;

    public Entry(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Entry{field=" + field + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return field == ((Entry) obj).field;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(field);
    }
}
