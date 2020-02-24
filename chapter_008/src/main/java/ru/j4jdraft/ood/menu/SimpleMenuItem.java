package ru.j4jdraft.ood.menu;

public class SimpleMenuItem implements MenuItem {
    private final String name;
    private Action action;
    private Renderer renderer;
    private int nestingLevel;

    public SimpleMenuItem(String name) {
        this(name, null, null, 0);
    }

    public SimpleMenuItem(String name, Action action) {
        this(name, action, null, 0);
    }

    public SimpleMenuItem(String name, Action action, Renderer renderer, int nestingLevel) {
        this.name = name;
        this.action = action;
        this.renderer = renderer;
        this.nestingLevel = nestingLevel;
    }

    @Override
    public int getLevel() {
        return nestingLevel;
    }

    @Override
    public void setLevel(int level) {
        nestingLevel = level;
    }

    @Override
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void display() {
        renderer.render(this);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void choose() {
        action.execute();
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
