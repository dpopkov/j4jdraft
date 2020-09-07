package ru.j4jdraft.mt.bomberman;

/**
 * Orthogonal direction on board.
 */
public enum Direction {
    UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

    private static final Direction[] VALUES = Direction.values();

    private final int dx;
    private final int dy;

    Direction(int dy, int dx) {
        this.dy = dy;
        this.dx = dx;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static Direction of(int deltaX, int deltaY) {
        if (deltaY == 0) {
            if (deltaX < 0) {
                return Direction.LEFT;
            } else if (deltaX > 0) {
                return Direction.RIGHT;
            }
        }
        if (deltaX == 0) {
            if (deltaY < 0) {
                return Direction.UP;
            } else if (deltaY > 0) {
                return Direction.DOWN;
            }
        }
        throw new IllegalArgumentException(
                String.format("Not orthogonal direction: (dY=%d,dX=%d)", deltaY, deltaX));
    }

    /** Returns random direction. */
    public static Direction random() {
        return VALUES[(int) (Math.random() * VALUES.length)];
    }
}
