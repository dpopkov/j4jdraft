package ru.j4jdraft.netw.bot;

/**
 * Constants used in the application.
 */
public interface Constants {
    /** Port number. */
    int PORT = 8000;

    /** New line string that designates end of client message. */
    String NL = "\n";

    /** The marker that designates the end of server response. */
    String END = NL + NL;

    /** Exit word that is used to stop communication with the server. */
    String EXIT_WORD = "bye";
}
