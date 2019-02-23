package ru.j4jdraft.io.chat;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Chat {
    private final Supplier<String> talker1;
    private final Supplier<String> talker2;
    private final Consumer<String> output;
    private final Consumer<String> logger;

    public Chat(Supplier<String> talker1, Supplier<String> talker2, Consumer<String> output, Consumer<String> logger) {
        this.talker1 = talker1;
        this.talker2 = talker2;
        this.output = output;
        this.logger = logger;
    }

    public void start() {
        boolean running = true;
        boolean responding = true;
        while (running) {
            String phrase = talker1.get();
            logger.accept(phrase);
            if ("stop".equals(phrase)) {
                responding = false;
            } else if ("continue".equals(phrase)) {
                responding = true;
            } else if ("quit".equals(phrase)) {
                running = false;
            } else if (responding){
                String response = talker2.get();
                output.accept(response);
                logger.accept(response);
            }
        }
    }
}
