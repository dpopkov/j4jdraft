package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server implements Runnable {
    private final Oracle oracle;
    private final int portNumber;
    private boolean listening;

    public Server(Oracle oracle, int portNumber) {
        this.oracle = oracle;
        this.portNumber = portNumber;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Listening on port " + portNumber);
            while (listening) {
                try (Socket incoming = serverSocket.accept()) {
                    Scanner in = new Scanner(incoming.getInputStream(), StandardCharsets.UTF_8);
                    PrintWriter out = new PrintWriter(incoming.getOutputStream(), true, StandardCharsets.UTF_8);
                    boolean serving = true;
                    while (serving) {
                        String question = in.nextLine();
                        if (question.equalsIgnoreCase("stop")) {
                            serving = false;
                            listening = false;
                        } else if (question.equalsIgnoreCase("bye")) {
                            serving = false;
                            out.println("Bye!");
                        } else {
                            String answer = oracle.reply(question);
                            out.println(answer);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        System.out.println("Server stopped.");
    }

    public boolean isListening() {
        return listening;
    }
}
