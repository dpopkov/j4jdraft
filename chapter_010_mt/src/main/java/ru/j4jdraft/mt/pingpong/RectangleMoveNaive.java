package ru.j4jdraft.mt.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Простейшая имплементация класса управляюещго движением прямоугольника.
 * Выход через флаг.
 * Перемещение прямоугольника происходит в отдельном потоке, вместо того чтобы
 * изменять "JavaFX scene graph from the UI thread also known as the JavaFX Application thread".
 * Поэтому эта версия является некорректным решением.
 */
public class RectangleMoveNaive extends RectangleMoveBase {
    private volatile boolean running = true;

    public RectangleMoveNaive(Rectangle rect, int width, long delay) {
        super(rect, width, delay);
    }

    @Override
    public void run() {
        int dx = 1;
        try {
            while (running) {
                dx = move(dx);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": RectangleMove interrupted");
        }
    }

    @Override
    public void stop() {
        System.out.println(Thread.currentThread().getName() + ": stopping");
        running = false;
    }
}
