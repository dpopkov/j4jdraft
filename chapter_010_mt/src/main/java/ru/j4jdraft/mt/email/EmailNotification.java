package ru.j4jdraft.mt.email;

import java.util.concurrent.ExecutorService;

@SuppressWarnings("unused")
public class EmailNotification {
    private final ExecutorService executorService;

    public EmailNotification(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /** Forms email to the user. */
    public void emailTo(User user) {
        String subj = String.format("Notification %s to email %s", user.getName(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getName());
        executorService.execute(() -> send(subj, body, user.getEmail()));
    }

    @SuppressWarnings("EmptyMethod")
    public void send(String subject, String body, String email) {
        // body is not implemented
    }

    /** Closes this service and any used resources. */
    public void close() {
        executorService.shutdown();
    }
}
