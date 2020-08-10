package ru.j4jdraft.mt.bomberman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private static final Logger LOG = LoggerFactory.getLogger(Board.class);
    private static final int LOCK_TIMEOUT = 500;

    private final ReentrantLock[][] locks;
    private final int size;

    public Board(int size, List<Position> blocks) {
        this.size = size;
        locks = initLocks(size);
        placeBlocks(blocks);
    }

    private void placeBlocks(List<Position> blocks) {
        for (Position block : blocks) {
            occupy(block);
        }
    }

    private ReentrantLock[][] initLocks(int size) {
        ReentrantLock[][] locks = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
        return locks;
    }

    public int size() {
        return size;
    }

    /**
     * Occupy the specified cell during initial setup.
     * This method is not supposed to be used when moving on board.
     */
    public void occupy(Position position) {
        ReentrantLock lock = getLockAt(position);
        if (!lock.tryLock()) {
            throw new IllegalStateException("Failed to occupy position: " + position);
        } else {
            LOG.trace("Locked: {}", position);
        }
    }

    /**
     * Tries to move from the source cell to the specified destination cell
     * or to another free neighbour cell.
     *
     * @param from source cell
     * @param to   destination cell
     * @return real destination cell if moved successfully
     * or null otherwise if failed to find a free cell
     * @throws InterruptedException if the operation was interrupted when trying to acquire lock
     */
    public Position move(Position from, Position to) throws InterruptedException {
        if (isInside(to) && getLockAt(to).tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
            unlockSource(from);
            return to;
        } else {
            // TODO make more attempts to move
            Position another = findAnother(from, from.directionTo(to));
            if (another == null) {
                return null;
            }
            ReentrantLock target = getLockAt(another);
            if (target.tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                unlockSource(from);
                return another;
            }
            return null;
        }
    }

    private void unlockSource(Position source) {
        ReentrantLock lock = getLockAt(source);
        LOG.trace("Unlocking: {}", source);
        lock.unlock();
        LOG.trace("Unlocked: {}", source);
    }

    private Position findAnother(Position from, Direction blocked) {
        DirectionChooser chooser = new ClockwiseDirectionChooser(blocked);
        Direction dir = chooser.next();
        while (dir != null) {
            Position candidate = from.inDirection(dir);
            if (isInside(candidate)) {
                return candidate;
            }
            dir = chooser.next();
        }
        return null;
    }

    private boolean isInside(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return col >= 0 && col < size && row >= 0 && row < size;
    }

    private ReentrantLock getLockAt(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        if (row < 0 || row == size || col < 0 || col == size) {
            throw new IllegalArgumentException("This cell is outside the boundaries: " + position);
        }
        return locks[row][col];
    }
}
