package ru.preachooda.bokunohero.dto.enumeration;

public enum Tier {

    SS("1.5"),
    S("1.2"),
    A("1"),
    B("0.8"),
    C("0.6"),
    D("0.4"),
    E("0.2"),
    F("0");

    private final String value;

    private Tier(String value) {
        this.value = value;
    }

}
