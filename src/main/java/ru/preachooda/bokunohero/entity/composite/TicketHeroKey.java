package ru.preachooda.bokunohero.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class TicketHeroKey implements Serializable {

    @Column(name = "ticket_id")
    Long ticketId;

    @Column(name = "hero_id")
    Long heroId;

}
