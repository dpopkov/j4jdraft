package ru.j4jdraft.netw.clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер слушает на порту " + port);
            try (Socket incoming = serverSocket.accept()) {
                System.out.println("Входящее соединение " + incoming.getInetAddress() + " порт " + incoming.getPort());
                try (DataInputStream in = new DataInputStream(incoming.getInputStream());
                     DataOutputStream out = new DataOutputStream(incoming.getOutputStream())) {
                    boolean serving = true;
                    while (serving) {
                        String received = in.readUTF();
                        System.out.println("Получено: " + received);
                        if (received.equalsIgnoreCase("exit")) {
                            serving = false;
                        }
                        String send = "ECHO: " + received;
                        out.writeUTF(send);
                        System.out.println("Отправлено: " + send);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
