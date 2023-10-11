package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.entity.Ticket;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class TicketMapper extends BaseEntityMapper<Ticket, TicketDto> {
}
