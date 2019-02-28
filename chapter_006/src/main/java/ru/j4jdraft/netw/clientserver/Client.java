package ru.j4jdraft.netw.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int serverPort = 5000;
        String host;
        if (args.length == 1) {
            host = args[0];
        } else {
            host = "localhost";
        }
        try (Socket socket = new Socket(host, serverPort)) {
            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 Scanner scanner = new Scanner(System.in)) {
                boolean running = true;
                while (running) {
                    System.out.print("Введите текст для отправки (exit для ост.сервера, quit для выхода): ");
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("quit")) {
                        running = false;
                    } else {
                        out.writeUTF(message);
                        String received = in.readUTF();
                        System.out.println("Получено от сервера: " + received);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
