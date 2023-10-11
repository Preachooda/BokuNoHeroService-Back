package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.repository.BaseEntityRepository;
import ru.preachooda.bokunohero.repository.TicketRepository;

@Service
public class TicketService extends BaseEntityService<Ticket> {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public BaseEntityRepository<Ticket> getRepository() {
        return ticketRepository;
    }
}
