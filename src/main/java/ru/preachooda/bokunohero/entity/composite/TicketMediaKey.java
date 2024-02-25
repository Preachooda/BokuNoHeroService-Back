package ru.preachooda.bokunohero.entity.composite;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class TicketMediaKey implements Serializable {

    @NotNull
    @Column(name = "ticket_id")
    Long ticketId;

    @NotNull
    @Column(name = "media_file_id")
    Long mediaFile;

}
