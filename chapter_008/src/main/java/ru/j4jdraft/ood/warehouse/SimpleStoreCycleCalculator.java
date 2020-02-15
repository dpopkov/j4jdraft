package ru.j4jdraft.ood.warehouse;

public class SimpleStoreCycleCalculator implements StoreCycleCalculator {
    private int topPercentForStore;
    private int topPercentForSale;

    public SimpleStoreCycleCalculator(int topPercentForStore, int topPercentForSale) {
        this.topPercentForStore = topPercentForStore;
        this.topPercentForSale = topPercentForSale;
    }

    public StoreCycle calculate(Expirable expirable) {
        return null;
    }
}
