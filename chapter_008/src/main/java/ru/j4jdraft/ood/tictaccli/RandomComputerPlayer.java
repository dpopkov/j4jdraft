package ru.j4jdraft.ood.tictaccli;

import java.util.BitSet;

public class RandomComputerPlayer implements Player {
    private final Mark mark;
    private final long delay;

    public RandomComputerPlayer(Mark mark, long delay) {
        this.mark = mark;
        this.delay = delay;
    }

    @Override
    public Position makeMove(GridView view) {
        pause();
        int count = 0;
        int numPositions = view.size() * view.size();
        BitSet triedIndexes = new BitSet(numPositions);
        while (count < numPositions) {
            int idx = (int) (Math.random() * numPositions);
            if (!triedIndexes.get(idx)) {
                triedIndexes.set(idx);
                count++;
                Position pos = new Position(idx / view.size(), idx % view.size());
                if (view.isFreeAt(pos)) {
                    return pos;
                }
            }
        }
        return null;
    }

    private void pause() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
