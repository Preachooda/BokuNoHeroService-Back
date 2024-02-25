package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.TicketMediaType;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ticket_media_file")
public class TicketMediaFile {

    @EmbeddedId
    private TicketMediaKey ticketMediaKey;

    @NotNull
    @Column(name = "media_type")
    @Enumerated(EnumType.STRING)
    private TicketMediaType mediaType;

}
