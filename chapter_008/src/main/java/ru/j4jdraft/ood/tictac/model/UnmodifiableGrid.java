package ru.j4jdraft.ood.tictac.model;

public class UnmodifiableGrid implements Grid {
    private final GameGrid decorated;

    public UnmodifiableGrid(GameGrid decorated) {
        this.decorated = decorated;
    }

    @Override
    public Mark getMark(Position position) {
        return decorated.getMark(position);
    }

    @Override
    public boolean isFreeAt(Position position) {
        return decorated.isFreeAt(position);
    }

    @Override
    public int size() {
        return decorated.size();
    }
}
