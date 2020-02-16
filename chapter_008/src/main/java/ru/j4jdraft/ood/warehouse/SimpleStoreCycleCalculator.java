package ru.j4jdraft.ood.warehouse;

public class SimpleStoreCycleCalculator implements StoreCycleCalculator {
    /** Top percent for store. */
    private int topForStore;
    /** Top percent for sale. */
    private int topForSale;

    public SimpleStoreCycleCalculator(int topForStore, int topForSale) {
        this.topForStore = topForStore;
        this.topForSale = topForSale;
    }

    public StoreCycle calculate(Expirable expirable) {
        return null;
    }
}
