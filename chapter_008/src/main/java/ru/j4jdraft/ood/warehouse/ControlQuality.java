package ru.j4jdraft.ood.warehouse;

import java.util.List;

/**
 * Распределяет продукты по приемникам в зависимости от состояния.
 */
public class ControlQuality {
    // todo: учесть будущее "Динамическое перераспределение продуктов [#854]"
    private Distribution distribution;
    private StoreCycleCalculator calculator;

    public ControlQuality(Distribution distribution, StoreCycleCalculator calculator) {
        this.distribution = distribution;
        this.calculator = calculator;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    // todo: test change of strategy
    public void setCalculator(StoreCycleCalculator calculator) {
        this.calculator = calculator;
    }

    public void distribute(List<Food> foodList) {
        for (Food food : foodList) {
            StoreCycle lifeCycle = calculator.calculate(food);
            distribution.accept(lifeCycle, food);
        }
    }

    /* извлекать все продукты и перераспределять их заново. */
    /*public void reSort() {

    }*/
}
