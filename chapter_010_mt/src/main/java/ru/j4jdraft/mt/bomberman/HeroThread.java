package ru.j4jdraft.mt.bomberman;

public class HeroThread extends Thread {
    private final Board board;
    private final long delay;
    private final Gui gui;

    public HeroThread(Board board, long delay, Gui gui) {
        this.board = board;
        this.delay = delay;
        this.gui = gui;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        Cell current = new Cell(board.size() / 2, board.size() / 2);
        board.occupy(current);
        if (gui != null) {
            gui.occupyOnGui(current);
        }
        boolean moving = true;
        while (!Thread.interrupted() && moving) {
            Cell to = current.inDirection(Direction.random());
            try {
                Cell newPos = board.move(current, to);
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
                Thread.currentThread().interrupt();
            }
        }
    }
}
