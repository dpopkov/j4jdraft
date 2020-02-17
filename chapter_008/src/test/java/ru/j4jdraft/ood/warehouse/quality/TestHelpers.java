package ru.j4jdraft.ood.warehouse.quality;

import java.time.LocalDate;

public class TestHelpers {
    public static Expirable stubExpirable(int creationDay, int expirationDay) {
        return new Expirable() {
            @Override
            public LocalDate created() {
                return stubDate(creationDay);
            }

            @Override
            public LocalDate expires() {
                return stubDate(expirationDay);
            }
        };
    }

    public static LocalDate stubDate(int day) {
        return LocalDate.of(2020, 1, day);
    }
}
