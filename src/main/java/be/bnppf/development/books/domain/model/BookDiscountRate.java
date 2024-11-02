package be.bnppf.development.books.domain.model;

import java.util.Arrays;

public enum BookDiscountRate {

    NONE(1, 0.0),
    FIVE_PERCENT(2, 0.05),
    TEN_PERCENT(3, 0.10),
    TWENTY_PERCENT(4, 0.20),
    MAX_DISCOUNT(5, 0.25);

    private final int uniqueBookCount;
    private final double rate;

    BookDiscountRate(int uniqueBookCount, double rate) {
        this.uniqueBookCount = uniqueBookCount;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public static double getDiscountRateForUniqueCount(int uniqueCount) {
        return Arrays.stream(BookDiscountRate.values())
                .filter(rate -> uniqueCount >= rate.uniqueBookCount)
                .map(BookDiscountRate::getRate)
                .reduce((first, second) -> second)
                .orElse(0.0);
    }
}
