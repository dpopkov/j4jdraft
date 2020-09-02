package ru.j4jdraft.djob.model;

public class Candidate {
    private int id;
    private final String name;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Candidate{id=" + id + ", name='" + name + '\'' + '}';
    }
}
