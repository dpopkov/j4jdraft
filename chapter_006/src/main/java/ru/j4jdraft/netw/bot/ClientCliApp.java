package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static ru.j4jdraft.netw.bot.Constants.*;

/**
 * Allows to enter requests in command line interface (CLI) and receive responses from a server.
 */
public class ClientCliApp {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", PORT);
            Scanner in = new Scanner(System.in);
            Client client = new Client(socket, in::nextLine, System.out::println);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
