package ru.j4jdraft.netw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Demonstrates Sockets.
 */
public class Whois {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("whois.internic.net", 43)) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String request = (args.length == 0 ? "MHProfessional.com" : args[0]) + "\n";
            byte[] bytes = request.getBytes();
            out.write(bytes);
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
