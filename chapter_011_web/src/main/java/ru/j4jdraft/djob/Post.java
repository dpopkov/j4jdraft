package ru.j4jdraft.djob;

import java.time.LocalDate;

public class Post {
    private final int id;
    private final String name;
    private final LocalDate created;

    public Post(int id, String name, LocalDate created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreated() {
        return created;
    }
}
