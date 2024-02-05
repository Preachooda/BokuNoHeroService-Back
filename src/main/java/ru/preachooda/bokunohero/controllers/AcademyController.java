package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.AcademyDto;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.mappers.AcademyMapper;
import ru.preachooda.bokunohero.services.AcademyService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/academies")
public class AcademyController extends BaseEntityController<Academy, AcademyDto> {

    @Autowired
    private AcademyService academyService;

    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public BaseEntityService<Academy> getService() {
        return academyService;
    }

    @Override
    public BaseEntityMapper<Academy, AcademyDto> getMapper() {
        return academyMapper;
    }

}
