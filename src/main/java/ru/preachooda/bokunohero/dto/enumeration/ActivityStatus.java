package ru.preachooda.bokunohero.dto.enumeration;

public enum ActivityStatus {

    CREATED("Создана"),
    IN_WORK("В работе"),
    EVALUATION("На оценке"),
    FINISHED("Завершена"),
    REJECTED("Отказ");

    private final String label;

    private ActivityStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
