package ru.j4jdraft.netw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Demonstrates datagrams.
 */
public class DatagramDemo {
    private static final int SERVER_PORT = 998;
    private static final int CLIENT_PORT = 999;
    private static final int BUFFER_SIZE = 1024;

    private static DatagramSocket ds;
    private static final byte[] buffer = new byte[BUFFER_SIZE];

    /** Creates sender. */
    private static void theServer() throws IOException {
        int pos = 0;
        boolean run = true;
        while (run) {
            int c = System.in.read();
            switch (c) {
                case -1:
                case 'q':
                    System.out.println("Server quits.");
                    ds.close();
                    run = false;
                case '\r':
                    break;
                case '\n':
                    ds.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), CLIENT_PORT));
                    pos = 0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
                    break;
            }
        }
    }

    /** Creates receiver. */
    private static void theClient() throws IOException {
        boolean run = true;
        while (run) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
            String message = new String(p.getData(), 0, p.getLength());
            System.out.println(message);
            if ("exit".equals(message)) {
                System.out.println("Closing the client");
                run = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Starting sender...");
            System.out.println("Send 'exit' to close the receiver. Type 'q' to quit the sender.");
            ds = new DatagramSocket(SERVER_PORT);
            theServer();
        } else {
            System.out.println("Starting receiver...");
            ds = new DatagramSocket(CLIENT_PORT);
            theClient();
        }
    }
}
