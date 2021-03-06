package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import static ru.j4jdraft.netw.bot.Constants.PORT;

/**
 * Application that stars a server for {@link WiseOracle}.
 */
public class WiseOracleServerApp {
    /** Path to resource file containing answers of the oracle. */
    private static final String XML_FILE = "/oracle_answers.xml";

    /**
     * Main method starting the application.
     * @param args not used
     */
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

    /**
     * Starts the server using the specified port.
     * @param port port number
     * @param answers collection of keyword-answer pairs
     * @throws IOException if an I/O error occurs opening the socket, waiting for the connection, or creating streams
     */
    public void start(int port, Map<String, String> answers) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket incoming = serverSocket.accept();
            Oracle oracle = new WiseOracle(answers);
            new Server(incoming, oracle).start();
        }
    }
}
