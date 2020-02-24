package ru.j4jdraft.ood.menu;

import java.util.function.Consumer;

public class ToStringRenderer implements Renderer {
    private final String indentationSymbol;
    private final Consumer<String> out;
    private final String suffix;

    public ToStringRenderer(String indentationSymbol, Consumer<String> out) {
        this(indentationSymbol, out, System.lineSeparator());
    }

    public ToStringRenderer(String indentationSymbol, Consumer<String> out, String suffix) {
        this.indentationSymbol = indentationSymbol;
        this.out = out;
        this.suffix = suffix;
    }

    @Override
    public void render(NestedItem item) {
        out.accept(indentationSymbol.repeat(item.getLevel())
                + item.name() + suffix);
    }
}
