package ru.j4jdraft.vacparser;

import java.time.LocalDateTime;

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
    /** Time of the vacancy's creation */
    private LocalDateTime created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
