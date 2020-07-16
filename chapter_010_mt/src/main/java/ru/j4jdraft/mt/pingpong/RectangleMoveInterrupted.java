package ru.j4jdraft.mt.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Простейшая имплементация класса управляюещго движением прямоугольника.
 * Выход через прерывание.
 * Перемещение прямоугольника происходит в отдельном потоке, вместо того чтобы
 * изменять "JavaFX scene graph from the UI thread also known as the JavaFX Application thread".
 * Поэтому эта версия является некорректным решением.
 */
public class RectangleMoveInterrupted extends RectangleMoveBase {
    private Thread movingThread;

    public RectangleMoveInterrupted(Rectangle rect, int width, long delay) {
        super(rect, width, delay);
    }

    @Override
    public void run() {
        movingThread = Thread.currentThread();
        int dx = 1;
        try {
            while (!Thread.interrupted()) {
                dx = move(dx);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": RectangleMove interrupted");
        }
    }

    @Override
    public void stop() {
        System.out.println(Thread.currentThread().getName() + ": stopping");
        if (movingThread != null) {
            movingThread.interrupt();
        }
    }
}
