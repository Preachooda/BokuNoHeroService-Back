package ru.preachooda.bokunohero.dto.enumeration;

public enum TicketPriority {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final Integer priority;

    private TicketPriority(Integer priority) {
        this.priority = priority;
    }

}
