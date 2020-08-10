package ru.j4jdraft.mt.bomberman;

import ru.j4jdraft.mt.bomberman.gui.LabelState;

public class HeroTask implements Runnable {
    private final Board board;
    private final Position initialPosition;
    private final long delay;
    private final Gui gui;

    public HeroTask(Board board, Position initialPosition, long delay, Gui gui) {
        this.board = board;
        this.initialPosition = initialPosition;
        this.delay = delay;
        this.gui = gui;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        Position current = initialPosition;
        board.occupy(current);
        if (gui != null) {
            gui.occupyOnGui(current, LabelState.HERO);
        }
        boolean moving = true;
        while (!Thread.interrupted() && moving) {
            Position to = current.inDirection(Direction.random());
            try {
                Position newPos = board.move(current, to);
                if (newPos == null) {
                    moving = false;
                } else {
                    if (gui != null) {
                        gui.updateGui(current, newPos);
                    }
                    current = newPos;
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " Interrupted");
                moving = false;
            }
        }
    }
}
