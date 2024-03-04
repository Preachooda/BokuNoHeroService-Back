package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.LicenseTicketDto;
import ru.preachooda.bokunohero.entity.LicenseTicket;
import ru.preachooda.bokunohero.mappers.LicenseTicketMapper;
import ru.preachooda.bokunohero.services.LicenseTicketService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/license-tickets")
public class LicenseTicketController extends BaseEntityController<LicenseTicket, LicenseTicketDto> {

    @Autowired
    private LicenseTicketService licenseTicketService;

    @Autowired
    private LicenseTicketMapper licenseTicketMapper;

    @Override
    public BaseEntityService<LicenseTicket> getService() {
        return licenseTicketService;
    }

    @Override
    public BaseEntityMapper<LicenseTicket, LicenseTicketDto> getMapper() {
        return licenseTicketMapper;
    }

}
