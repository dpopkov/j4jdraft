package ru.j4jdraft.ood.warehouse;

/** Используется для сортировки продуктов. */
public interface FoodSorter {
    /** Определяет id приемника продукта для сортировки. */
    DestId sort(Food food);
}
