package ru.j4jdraft.ood.warehouse;

import java.util.Date;

/** Represents entity with date of creation and expiration. */
public interface Expirable {
    Date created();

    Date expires();

}
