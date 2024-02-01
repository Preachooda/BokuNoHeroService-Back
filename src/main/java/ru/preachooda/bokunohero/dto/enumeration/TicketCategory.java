package ru.preachooda.bokunohero.dto.enumeration;

public enum TicketCategory {
    FIRE("Пожар"),
    FLOODING("Наводнение"),
    EARTHQUAKE("Землетрясение"),
    ROBBERY("Грабеж"),
    WHIMSY("Причуда"),
    OTHER("Другое");

    public final String label;

    private TicketCategory(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
