package ru.j4jdraft.ood.tictaccli;

/*
Игровой цикл
------------
0. Пользователь начинает игру.
1. Пользователь вводит координаты точки.
2. Сетка отображается с ходом пользователя.
2.1 Если конец игры, то остановка.
3. После задержки сетка отображается с ходом компьютера.
3.1 Если конец игры, то остановка.
4. Переход на начало цикла - п.1.
 */
public class TicTacCliMain {

    public static void main(String[] args) {
        ArgsConfig config = ArgsConfig.instance();
        config.init(args);
        GameCycle cycle = createGameCycle(config);
        cycle.start();
        System.out.println(cycle.getWinner() + " is the winner");
    }

    private static GameCycle createGameCycle(Config config) {
        Output output = new ConsoleOutput(new PseudoTextGridFormatter(), System.out);
        Input input = new ConsoleInput(output, System.in);
        Player human = new HumanPlayer(Mark.X, input);
        Player computer = new RandomComputerPlayer(Mark.O, config.getAnswerDelay());
        Player first;
        Player second;
        if (config.getStartingId() == 1) {
            first = human;
            second = computer;
        } else {
            first = computer;
            second = human;
        }
        GameGrid grid = new ArrayGrid(config.gridSize());
        return new GameCycle(grid, output, first, second, config.getWinLineLength());
    }
}
