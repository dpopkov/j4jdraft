package ru.j4jdraft.ood.tictac;

public class UnmodifiableGrid implements Grid {
    private final GameGrid decorated;

    public UnmodifiableGrid(GameGrid decorated) {
        this.decorated = decorated;
    }

    @Override
    public Mark getMark(int row, int col) {
        return decorated.getMark(row, col);
    }

    @Override
    public boolean isFreeAt(int row, int col) {
        return decorated.isFreeAt(row, col);
    }

    @Override
    public int size() {
        return decorated.size();
    }
}
