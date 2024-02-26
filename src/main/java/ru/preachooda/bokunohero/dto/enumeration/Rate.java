package ru.preachooda.bokunohero.dto.enumeration;

public enum Rate {

    NOT_RATED(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final Integer score;

    private Rate(Integer score) {
        this.score = score;
    }

}
