package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TicketHeroKeyDto implements Serializable {

    Long ticketId;

    Long heroId;

}
