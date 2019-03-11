package ru.j4jdraft.netw.bot;

public interface Constants {
    /** New line string that designates end of client message. */
    String NL = "\n";

    /** End of server response. */
    String END = NL + NL;

    /** Exit word that ends communication with server. */
    String EXIT_WORD = "bye";

    /** Port number. */
    int PORT = 8000;
}
