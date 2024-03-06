package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.mappers.HeroMapper;
import ru.preachooda.bokunohero.services.HeroService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;

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


    @GetMapping("/free")
    public ResponseEntity<List<HeroDto>> findAvailableHeroes() {
        return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityListToDtoList(heroService.findFreeHeroes()));
    }


}
