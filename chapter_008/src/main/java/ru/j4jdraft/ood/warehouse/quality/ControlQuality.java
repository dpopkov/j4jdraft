package ru.j4jdraft.ood.warehouse.quality;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Распределяет продукты по приемникам используя внешний алгоритм сортировки продуктов.
 */
public class ControlQuality<T extends Expirable & Discountable> {
    private static final Logger LOG = LogManager.getLogger(ControlQuality.class);

    private final Map<DestId, Consumer<T>> destinations = new EnumMap<>(DestId.class);
    private DestinationSorter<T> sortingStrategy;

    public ControlQuality(DestinationSorter<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void setSortingStrategy(DestinationSorter<T> sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void addDestination(DestId id, Consumer<T> destination) {
        destinations.put(id, destination);
    }

    public void sort(List<T> entityList) {
        for (T entity : entityList) {
            DestId id = sortingStrategy.sort(entity);
            Consumer<T> consumer = destinations.get(id);
            if (consumer != null) {
                LOG.info("{} goes to {}", entity, consumer);
                consumer.accept(entity);
            }
        }
    }
}
