package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.AcademyTicketDto;
import ru.preachooda.bokunohero.entity.AcademyTicket;
import ru.preachooda.bokunohero.mappers.AcademyTicketMapper;
import ru.preachooda.bokunohero.services.AcademyTicketService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/academy-tickets")
public class AcademyTicketController extends BaseEntityController<AcademyTicket, AcademyTicketDto> {

    @Autowired
    private AcademyTicketService academyTicketService;

    @Autowired
    private AcademyTicketMapper academyTicketMapper;

    @Override
    public BaseEntityService<AcademyTicket> getService() {
        return academyTicketService;
    }

    @Override
    public BaseEntityMapper<AcademyTicket, AcademyTicketDto> getMapper() {
        return academyTicketMapper;
    }

}
