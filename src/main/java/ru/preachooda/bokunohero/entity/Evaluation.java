package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.Rate;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;


@Data
@NoArgsConstructor
@Entity
@Table(name = "evaluation",
        uniqueConstraints = { @UniqueConstraint(name = "UniqueTicketHeroKey", columnNames = { "ticket_id", "hero_id" }) })
public class Evaluation {

    @EmbeddedId
    private TicketHeroKey ticketHeroKey;

    @Column(name = "rate")
    @Enumerated(EnumType.ORDINAL)
    private Rate rate = Rate.NOT_RATED;

    @Column(name = "comment")
    private String comment;

//    @Column(name = "is_speed")
//    @Convert(converter = TrueFalseConverter.class)
//    private Boolean isSpeed;
//
//    @Column(name = "is_power")
//    @Convert(converter = TrueFalseConverter.class)
//    private Boolean isPower;
//
//    @Column(name = "is_safe")
//    @Convert(converter = TrueFalseConverter.class)
//    private Boolean isSafe;


}
