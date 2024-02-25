package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.TrueFalseConverter;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;


@Data
@NoArgsConstructor
@Entity
@Table(name = "evaluation",
        uniqueConstraints = { @UniqueConstraint(name = "UniqueTicketHeroKey", columnNames = { "ticket_id", "hero_id" }) })
public class Evaluation {

    @EmbeddedId
    private TicketHeroKey ticketHeroKey;

    @Column(name = "score")
    private Integer score;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_speed")
    @Convert(converter = TrueFalseConverter.class)
    private Boolean isSpeed;

    @Column(name = "is_power")
    @Convert(converter = TrueFalseConverter.class)
    private Boolean isPower;

    @Column(name = "is_safe")
    @Convert(converter = TrueFalseConverter.class)
    private Boolean isSafe;


}
