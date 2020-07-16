package ru.j4jdraft.mt.pingpong;

import javafx.scene.shape.Rectangle;

public abstract class RectangleMoveBase implements RectangleMove {
    protected final Rectangle rect;
    protected final int width;
    protected final long delay;

    protected RectangleMoveBase(Rectangle rect, int width, long delay) {
        this.rect = rect;
        this.width = width;
        this.delay = delay;
    }

    protected int move(int dx) throws InterruptedException {
        rect.setX(rect.getX() + dx);
        if (rect.getX() >= width || rect.getX() < 0) {
            dx = -dx;
        }
        Thread.sleep(delay);
        return dx;
    }
}
