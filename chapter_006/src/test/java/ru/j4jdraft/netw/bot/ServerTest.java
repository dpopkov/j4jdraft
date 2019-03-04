package ru.j4jdraft.netw.bot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Verifier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ServerTest {
    private static final int PORT = 8000;
    private Server server;

    @Rule
    public TestRule serverStopped = new Verifier() {
        @Override
        protected void verify() {
            assertNotNull(server);
            assertFalse(server.isListening());
        }
    };

    @Test
    public void whenStartThenSendsAnswers() throws IOException {
        server = new Server(request -> "Test is ok.", PORT);
        server.start();
        try (Socket socket = new Socket("localhost", PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream())) {
            out.println("Test");
            String response = in.nextLine();
            // TODO: server must send empty string to end response
            out.println("stop");
            assertThat(response, is("Test is ok."));
        }
    }

    @Test
    public void whenIOExceptionThenPrintsStackTraceToPrintStream() {
        server = new Server(request -> "Test is ok.", PORT);
    }

    // TODO: test long responses
}
