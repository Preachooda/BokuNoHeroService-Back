package ru.preachooda.bokunohero.constants;

import ru.preachooda.bokunohero.dto.enumeration.QuirkType;
import ru.preachooda.bokunohero.dto.enumeration.TicketCategory;
import ru.preachooda.bokunohero.dto.enumeration.TicketComplexity;
import ru.preachooda.bokunohero.dto.enumeration.Tier;

import java.util.Map;


/**
 * Класс с константами для алгоритма машинного обучения для поиска героев
 */
public final class MatchingAlgorithmMLConstants {

    /**
     * Категории заявки
     */
    public final static Map<String, Double> categoryMap = Map.of(
            TicketCategory.FIRE.name(), 1d,
            TicketCategory.FLOODING.name(), 2d,
            TicketCategory.EARTHQUAKE.name(), 3d,
            TicketCategory.ROBBERY.name(), 4d,
            TicketCategory.QUIRK.name(), 5d,
            TicketCategory.OTHER.name(), 6d
    );

    /**
     * Типы причуд
     */
    public final static Map<String, Double> quirkTypesMap = Map.of(
            QuirkType.REINFORCEMENT.name(), 1d,
            QuirkType.CONTROL.name(), 2d,
            QuirkType.DESTRUCTION.name(), 3d,
            QuirkType.SUPPORT.name(), 4d,
            QuirkType.MUTATION.name(), 5d
    );

    /**
     * Уровни владения причудой
     */
    public final static Map<String, Double> quirkLevelsMap = Map.of(
            Tier.SS.name(), 1d,
            Tier.S.name(), 2d,
            Tier.A.name(), 3d,
            Tier.B.name(), 4d,
            Tier.C.name(), 5d,
            Tier.D.name(), 6d,
            Tier.E.name(), 7d,
            Tier.F.name(), 8d
    );

    /**
     * Сложность заявки
     */
    public final static Map<String, Double> complexityMap = Map.of(
            TicketComplexity.VERY_EASY.name(), 1d,
            TicketComplexity.EASY.name(), 2d,
            TicketComplexity.HARD.name(), 3d,
            TicketComplexity.VERY_HARD.name(), 4d
    );

}
