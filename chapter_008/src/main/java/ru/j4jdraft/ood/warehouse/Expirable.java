package ru.j4jdraft.ood.warehouse;

import java.util.Date;

public interface Expirable {
    Date created();

    Date expires();

}
