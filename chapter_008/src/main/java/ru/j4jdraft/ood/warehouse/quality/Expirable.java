package ru.j4jdraft.ood.warehouse.quality;

import java.time.LocalDate;

/** Represents entity with date of creation and expiration. */
public interface Expirable {
    LocalDate created();

    LocalDate expires();

}
