package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.mappers.HeroMapper;
import ru.preachooda.bokunohero.services.HeroService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/heroes")
public class HeroController extends BaseEntityController<Hero, HeroDto> {

    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroMapper heroMapper;

    @Override
    public BaseEntityService<Hero> getService() {
        return heroService;
    }

    @Override
    public BaseEntityMapper<Hero, HeroDto> getMapper() {
        return heroMapper;
    }
}
