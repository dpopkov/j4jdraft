package ru.j4jdraft.ood.tictac.cli;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class HumanTextView {
    private static final String NL = System.lineSeparator();

    private final Consumer<String> out;
    private final Supplier<String> in;
    private final HumanCliController controller;

    public HumanTextView(Consumer<String> out, Supplier<String> in, HumanCliController controller) {
        this.in = in;
        this.out = out;
        this.controller = controller;
    }

    public void start() {
        boolean playing = true;
        while (playing) {
            String grid = controller.getGrid();
            out.accept(grid + NL);
            out.accept("Enter row and column: ");
            String coords = in.get();
            boolean win = controller.move(coords);
            playing = !win;
        }
    }
}
