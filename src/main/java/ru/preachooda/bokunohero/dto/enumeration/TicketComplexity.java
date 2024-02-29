package ru.preachooda.bokunohero.dto.enumeration;

public enum TicketComplexity {

    VERY_EASY("Очень легкий"),
    EASY("Легкий"),
    HARD("Сложный"),
    VERY_HARD("Очень сложный");

    public final String label;

    private TicketComplexity(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
