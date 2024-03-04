package ru.preachooda.bokunohero.dto.enumeration;

public enum QuirkType {

    REINFORCEMENT("Усиление"),
    CONTROL("Управление"),
    DESTRUCTION("Разрушение"),
    SUPPORT("Вспомогательные"),
    MUTATION("Мутация");

    private final String label;

    private QuirkType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
