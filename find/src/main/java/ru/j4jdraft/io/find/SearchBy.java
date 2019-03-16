package ru.j4jdraft.io.find;

/** Type of search. */
public enum SearchBy {
    /** Search by mask where the mask is a glob pattern using wildcards. */
    MASK("glob"),
    /** Search by full name. */
    FULL("full"),
    /** Search using regular expression. */
    REGEX("regex");

    private final String syntax;

    SearchBy(String syntax) {
        this.syntax = syntax;
    }

    public String getSyntax() {
        return syntax;
    }
}
