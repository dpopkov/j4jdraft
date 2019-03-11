package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import static ru.j4jdraft.netw.bot.Constants.PORT;

public class WiseOracleServerApp {
    private static final String XML_FILE = "/oracle_answers.xml";

    public static void main(String[] args) {
        try {
            DataLoader data = new DataLoader();
            data.loadFromXML(WiseOracleServerApp.class.getResourceAsStream(XML_FILE));
            Map<String, String> answers = data.getMap();
            new WiseOracleServerApp().start(PORT, answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int port, Map<String, String> answers) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket incoming = serverSocket.accept();
            Oracle oracle = new WiseOracle(answers);
            new Server(incoming, oracle).start();
        }
    }
}
