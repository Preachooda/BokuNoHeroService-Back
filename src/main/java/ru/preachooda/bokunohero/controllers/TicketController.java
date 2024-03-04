package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.mappers.TicketMapper;
import ru.preachooda.bokunohero.services.TicketService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;

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

    // TODO: 03.03.2024 Сохранять Evaluation по тикету, если heroRate не пустой
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketDto>> findAllByUserId(@PathVariable("userId") Long userId) {
        List<Ticket> entityList = ticketService.findAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityListToDtoList(entityList));

    }

    @GetMapping("/hero/{heroId}/activeTicket")
    public ResponseEntity<TicketDto> findActiveTicketByHeroId(@PathVariable("heroId") Long heroId) {
        return ResponseEntity.ok().body(ticketMapper.entityToDto(ticketService.findActiveTicketByHeroId(heroId)));
    }

}
