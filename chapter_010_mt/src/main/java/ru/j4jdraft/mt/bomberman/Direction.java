package ru.j4jdraft.mt.bomberman;

/**
 * Orthogonal direction on board.
 */
enum Direction {
    UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1);

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
        if (deltaX < 0) {
            return Direction.LEFT;
        } else if (deltaX > 0) {
            return Direction.RIGHT;
        } else if (deltaY < 0) {
            return Direction.UP;
        } else if (deltaY > 0) {
            return Direction.DOWN;
        } else {
            throw new IllegalArgumentException(
                    String.format("Not orthogonal direction: (dY=%d,dX=%d)", deltaY, deltaX));
        }
    }

    /** Returns random direction. */
    public static Direction random() {
        Direction[] values = Direction.values();
        int i = (int) (Math.random() * values.length);
        return values[i];
    }
}
