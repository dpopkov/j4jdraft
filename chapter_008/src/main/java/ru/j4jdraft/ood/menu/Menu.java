package ru.j4jdraft.ood.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu implements NestedItem {
    private final List<NestedItem> items = new ArrayList<>();
    private final String name;
    private Renderer renderer;
    private int nestingLevel;

    public Menu(String name) {
        this.name = name;
    }

    public Menu(String name, Renderer renderer) {
        this.name = name;
        this.renderer = renderer;
    }

    @Override
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void display() {
        renderer.render(this);
        for (NestedItem item : items) {
            item.display();
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int getLevel() {
        return nestingLevel;
    }

    @Override
    public void setLevel(int level) {
        nestingLevel = level;
    }

    public void addItem(NestedItem item) {
        item.setLevel(this.getLevel() + 1);
        item.setRenderer(this.renderer);
        items.add(item);
    }
}
