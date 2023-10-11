package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.EnumerationValueDto;
import ru.preachooda.bokunohero.entity.EnumerationValue;
import ru.preachooda.bokunohero.mappers.BaseEntityMapper;
import ru.preachooda.bokunohero.mappers.EnumerationValueMapper;
import ru.preachooda.bokunohero.services.BaseEntityService;
import ru.preachooda.bokunohero.services.EnumerationValueService;

@RestController
@RequestMapping("/enumeration-values")
public class EnumerationValueController extends BaseEntityController<EnumerationValue, EnumerationValueDto> {

    @Autowired
    private EnumerationValueService enumerationService;

    @Autowired
    private EnumerationValueMapper enumerationMapper;

    @Override
    public BaseEntityService<EnumerationValue> getService() {
        return enumerationService;
    }

    @Override
    public BaseEntityMapper<EnumerationValue, EnumerationValueDto> getMapper() {
        return enumerationMapper;
    }

}
