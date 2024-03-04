package ru.preachooda.bokunohero.dto.enumeration;

public enum ActivityStatus {

    CREATED("Создана"),
    ASSIGNED("Назначена"),
    IN_WORK("В работе"),
    EVALUATION("На оценке"),
    COMPLETED("Завершена"),
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
