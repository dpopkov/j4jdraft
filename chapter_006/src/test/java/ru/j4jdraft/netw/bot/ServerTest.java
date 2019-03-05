package ru.j4jdraft.netw.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServerTest {
    private static final String NL = Server.NL;

    @Test
    public void whenStartThenSendsAnswers() throws IOException {
        String clientInput = String.join(NL,"Test", "bye", "");
        ByteArrayInputStream in = new ByteArrayInputStream(clientInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket, request -> "Test is ok.");
        server.start();
        String expectedInput = String.join(NL,"Test is ok.", "", "Bye!", NL);
        assertThat(out.toString(), is(expectedInput));
    }

    @Test
    public void whenStartThenCanSendMultilineMessages() throws IOException {
        String clientInput = String.join(NL,"Hello", "bye", "");
        ByteArrayInputStream in = new ByteArrayInputStream(clientInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket, request -> String.join(NL ,"response line1", "response line2"));
        server.start();
        String expectedInput = String.join(NL,"response line1", "response line2", "", "Bye!", NL);
        assertThat(out.toString(), is(expectedInput));
    }
}
