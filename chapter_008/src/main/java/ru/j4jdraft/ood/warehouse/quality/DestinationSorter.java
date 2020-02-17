package ru.j4jdraft.ood.warehouse.quality;

/** Используется для определения местаназначения объектов со сроком годности. */
public interface DestinationSorter<T extends Expirable & Discountable> {
    /** Определяет id приемника объекта для сортировки. */
    DestId sort(T entity);
}
