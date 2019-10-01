package ru.j4jdraft.vacparser.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Содержит данные о вакансии
 */
public class Vacancy {
    /** Primary key in database. */
    private Integer id;
    /** Name or title of the vacancy. */
    private String name;
    /** Description of the vacancy. */
    private String description;
    /** Link to the vacancy. */
    private String link;
    /** Time of the vacancy's creation */
    private LocalDateTime created;

    public Vacancy(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Vacancy(String name, String description, String link, LocalDateTime created) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) obj;
        return id.equals(vacancy.id) && name.equals(vacancy.name) && description.equals(vacancy.description)
                && link.equals(vacancy.link) && created.equals(vacancy.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, link, created);
    }

    @Override
    public String toString() {
        return "Vacancy{id=" + id + ", name='" + name + "', description='" + description
                + "', link='" + link + "', created=" + created + '}';
    }
}
