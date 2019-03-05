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
    public void whenStartAndByeThenStopsImmediately() throws IOException {
        testServerResponse(
                "bye" + NL,
                "irrelevant",
                String.join(NL, "Bye!", "", "")
        );
    }

    @Test
    public void whenStartThenSendsAnswers() throws IOException {
        testServerResponse(
                String.join(NL,"Test", "bye", ""),
                "Test is ok.",
                String.join(NL,"Test is ok.", "", "Bye!", "", "")
        );
    }

    @Test
    public void whenStartThenCanSendMultilineMessages() throws IOException {
        testServerResponse(
                String.join(NL,"Hello", "BYE", ""),
                String.join(NL ,"response line1", "response line2"),
                String.join(NL,"response line1", "response line2", "", "Bye!", "", "")
        );
    }

    private void testServerResponse(String clientInput, String response, String expectedResponse) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(buffer);
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(clientInput.getBytes()));
        new Server(socket, request -> response).start();
        assertThat(buffer.toString(), is(expectedResponse));
    }
}
