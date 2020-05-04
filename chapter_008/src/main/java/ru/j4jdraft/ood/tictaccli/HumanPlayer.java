package ru.j4jdraft.ood.tictaccli;

public class HumanPlayer implements Player {
    private final Mark mark;
    private final Output output;
    private final Input input;

    public HumanPlayer(Mark mark, Output output, Input input) {
        this.mark = mark;
        this.output = output;
        this.input = input;
    }

    @Override
    public Position makeMove(GridView view) {
        // todo: display grid
        // todo: receive input
        return null;
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
