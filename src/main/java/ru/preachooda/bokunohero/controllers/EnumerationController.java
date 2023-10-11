package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.EnumerationDto;
import ru.preachooda.bokunohero.entity.Enumeration;
import ru.preachooda.bokunohero.mappers.BaseEntityMapper;
import ru.preachooda.bokunohero.mappers.EnumerationMapper;
import ru.preachooda.bokunohero.services.BaseEntityService;
import ru.preachooda.bokunohero.services.EnumerationService;

@RestController
@RequestMapping("/enumerations")
public class EnumerationController extends BaseEntityController<Enumeration, EnumerationDto> {

    @Autowired
    private EnumerationService enumerationService;

    @Autowired
    private EnumerationMapper enumerationMapper;

    @Override
    public BaseEntityService<Enumeration> getService() {
        return enumerationService;
    }

    @Override
    public BaseEntityMapper<Enumeration, EnumerationDto> getMapper() {
        return enumerationMapper;
    }
}
