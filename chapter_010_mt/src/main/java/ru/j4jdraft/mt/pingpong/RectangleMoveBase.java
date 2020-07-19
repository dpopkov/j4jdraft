package ru.j4jdraft.mt.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Base class that moves rectangle.
 * Methods {@link #stop()} and {@link #run()} must be overridden in a subclass that will
 * be responsible for stopping this moving task.
 */
public abstract class RectangleMoveBase implements RectangleMove {
    private final Rectangle rect;
    private final int width;
    private final long delay;

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

    @Override
    public abstract void stop();

    @Override
    public abstract void run();
}
