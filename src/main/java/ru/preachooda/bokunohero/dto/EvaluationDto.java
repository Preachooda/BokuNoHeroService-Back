package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EvaluationDto {

    private TicketHeroKeyDto ticketHeroKey;

    private Integer score;

    private String comment;

    private Boolean isSpeed;

    private Boolean isPower;

    private Boolean isSafe;

}
