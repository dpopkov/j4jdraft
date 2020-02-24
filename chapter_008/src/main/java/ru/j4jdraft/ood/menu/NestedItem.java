package ru.j4jdraft.ood.menu;

public interface NestedItem extends Item {

    int getLevel();

    void setLevel(int level);

    void setRenderer(Renderer renderer);
}
