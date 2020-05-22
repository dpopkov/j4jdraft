package ru.j4jdraft.ood.tictaccli;

/**
 * Factory that can create a human or random computer player.
 */
public class HumanVsRandomPlayerFactory implements PlayerFactory {

    /** Creates player with the specified parameters. */
    @Override
    public Player create(Mark mark, Config.PlayerType type, Input input) {
        if (type == Config.PlayerType.HUMAN) {
            return new HumanPlayer(mark, input);
        } else if (type == Config.PlayerType.COMPUTER) {
            return new RandomComputerPlayer(mark);
        }
        throw new IllegalArgumentException("Invalid player type: " + type);
    }
}
