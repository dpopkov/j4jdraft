package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static ru.j4jdraft.netw.bot.Constants.*;

public class Server {
    private static final String END = NL + NL;

    private final Oracle oracle;
    private final Socket socket;

    public Server(Socket socket, Oracle oracle) {
        this.socket = socket;
        this.oracle = oracle;
    }

    public void start() throws IOException {
        try (Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {
            boolean serving = true;
            while (serving) {
                String question = in.nextLine();
                if (question.equalsIgnoreCase(EXIT_WORD)) {
                    serving = false;
                    send(out, "Bye!");
                } else {
                    String answer = oracle.reply(question);
                    send(out, answer);
                }
            }
        }
    }

    private void send(PrintWriter out, String message) {
        out.print(message);
        out.print(END);
        out.flush();
    }
}
