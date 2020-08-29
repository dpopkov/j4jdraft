package ru.j4jdraft.djob.model;

public class Candidate {
    private final int id;
    private final String name;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
