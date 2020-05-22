package ru.j4jdraft.ood.tictaccli;

/**
 * Factory that can create players for the game.
 */
public interface PlayerFactory {

    /** Creates player with the specified parameters. */
    Player create(Mark mark, Config.PlayerType type, Input input);
}
