package ru.j4jdraft.ood.tictaccli;

public class Main {
    public static void main(String[] args) {
        ArgsConfig config = ArgsConfig.instance();
        config.init(args);
        Game game = new Game(config, System.out, System.in);
        game.run();
    }
}
