package ru.j4jdraft.ood.tictaccli;

/**
 * Main class that starts the application.<br>
 * It can use command line arguments:<br>
 * <pre>
 * -s sizeOfGrid (default value is 3)
 * -f firstPlayerStartingTheGame ('human' or 'computer')
 * -w winningLineLength (default value is 3)
 * -d computerDelayInMilliseconds (default value is 700)
 * </pre>
 */
public class TicTacToeMain {
    public static void main(String[] args) {
        ArgsConfig config = ArgsConfig.instance();
        config.init(args);
        Game game = new Game(config, System.out, System.in);
        game.run();
    }
}
