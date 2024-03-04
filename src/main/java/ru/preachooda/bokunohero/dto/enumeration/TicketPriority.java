package ru.preachooda.bokunohero.dto.enumeration;

import java.util.Arrays;

public enum TicketPriority {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final Integer priority;

    TicketPriority(Integer priority) {
        this.priority = priority;
    }

    public static TicketPriority getPriorityByValue(Integer priority) {
        return Arrays.stream(TicketPriority.values())
                .filter(enumStateValue -> enumStateValue.priority.equals(priority))
                .findFirst()
                .orElse(null);
    }

}
