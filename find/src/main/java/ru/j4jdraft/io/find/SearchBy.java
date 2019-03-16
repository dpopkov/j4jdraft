package ru.j4jdraft.io.find;

/** Type of search. */
public enum SearchBy {
    /** Search by mask where the mask is a glob pattern using wildcards. */
    MASK,
    /** Search by full name. */
    FULL,
    /** Search using regular expression. */
    REGEX
}
