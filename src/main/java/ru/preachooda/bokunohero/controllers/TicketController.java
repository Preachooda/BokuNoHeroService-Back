package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.mappers.BaseEntityMapper;
import ru.preachooda.bokunohero.mappers.TicketMapper;
import ru.preachooda.bokunohero.services.BaseEntityService;
import ru.preachooda.bokunohero.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController extends BaseEntityController<Ticket, TicketDto> {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public BaseEntityService<Ticket> getService() {
        return ticketService;
    }

    @Override
    public BaseEntityMapper<Ticket, TicketDto> getMapper() {
        return ticketMapper;
    }

}
