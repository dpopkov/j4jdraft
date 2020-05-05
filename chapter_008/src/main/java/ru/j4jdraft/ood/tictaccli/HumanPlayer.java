package ru.j4jdraft.ood.tictaccli;

public class HumanPlayer implements Player {
    private final Mark mark;
    private final Input input;

    public HumanPlayer(Mark mark, Input input) {
        this.mark = mark;
        this.input = input;
    }

    @Override
    public Position makeMove(GridView view) {
        return input.requestPosition("Enter row and column: ");
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
