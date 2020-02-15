package ru.j4jdraft.ood.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Trash implements FoodConsumer {
    private static final Logger LOG = LogManager.getLogger(Trash.class);

    @Override
    public void accept(Food food) {
        LOG.trace("{} goes to trash", food);
    }
}
