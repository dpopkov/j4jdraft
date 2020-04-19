package ru.j4jdraft.ood.tictac.model;

public interface GridObserver {
    void update(Position position, Mark mark);
}
