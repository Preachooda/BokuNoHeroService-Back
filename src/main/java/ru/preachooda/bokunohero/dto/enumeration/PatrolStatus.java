package ru.preachooda.bokunohero.dto.enumeration;

public enum PatrolStatus {
    PENDING("В процессе"),
    STARTED("Начат"),
    COMPLETED("Завершен");

    private String label;

    private PatrolStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
