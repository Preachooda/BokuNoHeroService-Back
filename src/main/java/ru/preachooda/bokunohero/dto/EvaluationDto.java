package ru.preachooda.bokunohero.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
