package ru.j4jdraft.ood.tictac.model;

import java.util.Objects;

public class PlayerId {
    private final int id;
    private final Mark mark;

    public PlayerId(int id, Mark mark) {
        this.id = id;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlayerId playerId = (PlayerId) obj;
        return id == playerId.id && mark == playerId.mark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark);
    }
}
