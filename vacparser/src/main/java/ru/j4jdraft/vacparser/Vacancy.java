package ru.j4jdraft.vacparser;

/**
 * Содержит данные о вакансии
 */
public class Vacancy {
    /** Primary key in database. */
    private int id;
    /** Name or title of the vacancy. */
    private String name;
    /** Description of the vacancy. */
    private String description;
    /** Link to the vacancy. */
    private String link;
    /** Number of milliseconds which represents time of the vacancy's creation */
    private long created;
}
