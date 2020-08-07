package ru.j4jdraft.mt.bomberman;

/**
 * Chooses directions in clockwise direction.
 */
public class ClockwiseDirectionChooser implements DirectionChooser {
    private final Direction[] options = Direction.values().clone();
    private int next;

    /**
     * Constructs the chooser that will not use the specified blocked direction.
     */
    public ClockwiseDirectionChooser(Direction blocked) {
        initUsingBlockedOption(blocked);
    }

    private void initUsingBlockedOption(Direction blocked) {
        for (int i = 0; i < options.length; i++) {
            if (options[i] == blocked) {
                options[i] = null;
                next = i + 1;
                next = next % options.length;
                break;
            }
        }
    }

    /** Returns next direction or null if all possible directions were returned. */
    @Override
    public Direction next() {
        Direction nextDirection = options[next];
        int numOptions = options.length - 1;
        for (int i = 0; nextDirection == null && i < numOptions; i++) {
            advanceNext();
            nextDirection = options[next];
        }
        options[next] = null;
        advanceNext();
        return nextDirection;
    }

    private void advanceNext() {
        next++;
        next = next % options.length;
    }
}
