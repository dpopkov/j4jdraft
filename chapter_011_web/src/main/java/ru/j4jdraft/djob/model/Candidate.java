package ru.j4jdraft.djob.model;

public class Candidate {
    private int id;
    private final String name;
    private final String photoId;

    public Candidate(int id, String name, String photoId) {
        this.id = id;
        this.name = name;
        this.photoId = photoId;
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

    public String getPhotoId() {
        return photoId;
    }

    @Override
    public String toString() {
        return "Candidate{id=" + id + ", name='" + name + '\'' + ", photoId='" + photoId + '\'' + '}';
    }
}
