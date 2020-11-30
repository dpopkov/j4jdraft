package ru.job4jdraft.cinema.model;

import java.util.Objects;

public class Hall {
    private int id;
    private int seat;

    public Hall(int id, int seat) {
        this.id = id;
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        Hall hall = (Hall) o;
        return id == hall.id &&
                seat == hall.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seat);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", seat=" + seat +
                '}';
    }
}
