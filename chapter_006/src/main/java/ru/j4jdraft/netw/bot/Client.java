package ru.j4jdraft.netw.bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static ru.j4jdraft.netw.bot.Constants.*;

/**
 * Client that allows to send questions and receive responses using a socket.
 */
public class Client {
    private final Socket socket;
    private final Supplier<String> questions;
    private final Consumer<String> responseConsumer;

    public Client(Socket socket, Supplier<String> questions, Consumer<String> responseConsumer) {
        this.socket = socket;
        this.questions = questions;
        this.responseConsumer = responseConsumer;
    }

    public void start() throws IOException {
        try (Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {
            boolean speaking = true;
            StringBuilder builder = new StringBuilder();
            while (speaking) {
                String question = questions.get();
                out.print(question);
                out.print(NL);
                out.flush();
                builder.setLength(0);
                String line;
                boolean inline = true;
                while (in.hasNextLine() && inline) {
                    line = in.nextLine();
                    if (line.isEmpty()) {
                        inline = false;
                    } else {
                        if (builder.length() > 0) {
                            builder.append(NL);
                        }
                        builder.append(line);
                    }
                }
                responseConsumer.accept(builder.toString());
                if (question.equalsIgnoreCase(EXIT_WORD)) {
                    speaking = false;
                }
            }
        }
    }
}
