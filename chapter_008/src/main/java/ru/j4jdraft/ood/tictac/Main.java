package ru.j4jdraft.ood.tictac;

import ru.j4jdraft.ood.tictac.cli.CliMain;
import ru.j4jdraft.ood.tictac.fx.MainWindow;

public class Main {
    /*
    Привести default конфигурацию.
     */
    public static void main(String[] args) {
        // 1) Enter type of players
        // Example: player1=human player2=computer OR player1=computer player2=computer
        // 2) Enter size of grid
        // Example: size=3
        // 3) Enter number of marks in winning line
        // Example: marks=3
        // 4) Enter who starts
        // Example: starts=player1
        // 5) Enter UI type for human
        // Example: ui=console OR ui=fx
        Config.instance().init(args);
        String uiType = Config.instance().uiType();
        if ("fx".equals(uiType)) {
            MainWindow.main(args);
        } else if ("console".equals(uiType)) {
            CliMain.main(args);
        } else {
            System.err.println("Other types of UI not implemented yet");
        }

        // Create model (модель не зависит от интерфейса)
        // Create controller (зависит от интерфейса)
        // Create view (зависит от интерфейса)
    }
}
