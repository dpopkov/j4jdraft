package ru.j4jdraft.ood.warehouse.quality;

/** Определяет состояние продукта в зависимости от срока годности. */
public interface StoreCycleCalculator {
    StoreCycle calculate(Expirable expirable);
}
