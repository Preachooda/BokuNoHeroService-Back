package ru.preachooda.bokunohero.dto.enumeration;

import java.util.Arrays;

public enum Rate {

    NOT_RATED(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final Integer rate;

    private Rate(Integer rate) {
        this.rate = rate;
    }

    public static Rate getRateByValue(Integer rate) {
        return Arrays.stream(Rate.values())
                .filter(enumStateValue -> enumStateValue.rate.equals(rate))
                .findFirst()
                .orElse(null);
    }

}
