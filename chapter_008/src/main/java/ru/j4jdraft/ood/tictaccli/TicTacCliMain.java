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
        GameGrid grid = new ArrayGrid(config.gridSize());
        Player dummy = new Player() {
            @Override
            public Position makeMove(GridView view) {
                return new Position(0, 0);
            }

            @Override
            public Mark getMark() {
                return Mark.X;
            }
        };
        GameCycle cycle = new GameCycle(grid, dummy, dummy, config.getWinLineLength());
        cycle.start();
    }
}
