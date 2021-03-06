package ru.j4jdraft.ood.tictac;

import ru.j4jdraft.ood.tictac.cli.CliMain;
import ru.j4jdraft.ood.tictac.fx.MainWindow;
import ru.j4jdraft.ood.tictac.model.Config;

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
        ArgsConfig.instance().init(args);
        Config config = ArgsConfig.instance();
        String uiType = config.uiType();
        if ("fx".equals(uiType)) {
            MainWindow.setConfig(config);
            MainWindow.main(args);
        } else if ("console".equals(uiType)) {
            new CliMain(config).start();
        } else {
            System.err.println("Other types of UI not implemented yet");
        }

        // Create model (модель не зависит от интерфейса)
        // Create controller (зависит от интерфейса)
        // Create view (зависит от интерфейса)
    }
}
