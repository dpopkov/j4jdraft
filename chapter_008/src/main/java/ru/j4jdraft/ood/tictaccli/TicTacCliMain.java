package ru.j4jdraft.ood.tictaccli;

import java.util.Scanner;

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
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArgsConfig config = ArgsConfig.instance();
        config.init(args);
        GameCycle cycle = createGameCycle(config);
        cycle.start();
        System.out.println(cycle.getWinner() + " is the winner");
    }

    private static GameCycle createGameCycle(ArgsConfig config) {
        GameGrid grid = new ArrayGrid(config.gridSize());
        Output output = System.out::print;
        Input input = prompt -> {
            System.out.print(prompt);
            return scanner.nextLine();
        };
        Player human = new HumanPlayer(Mark.X, output, input);
        Player computer = new RandomComputerPlayer(Mark.O, output, 500L);
        Player first;
        Player second;
        if (config.getStartingId() == 1) {
            first = human;
            second = computer;
        } else {
            first = computer;
            second = human;
        }
        return new GameCycle(grid, first, second, config.getWinLineLength());
    }
}
