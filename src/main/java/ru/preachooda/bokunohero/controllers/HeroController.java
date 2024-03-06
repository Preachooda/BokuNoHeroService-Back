package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.mappers.HeroMapper;
import ru.preachooda.bokunohero.services.HeroService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/auto")
    ResponseEntity<List<HeroDto>> findSuitableHeroes(@RequestBody TicketDto ticketDto) {
        List<Hero> heroList = heroService.findSuitableHeroes(ticketDto);
        List<Hero> result = new ArrayList<>();
        if (heroList.size() > 3) {
            result = heroList.stream()
                    .sorted(Comparator.comparing(Hero::getRankingPosition))
                    .limit(3)
                    .collect(Collectors.toList());
        } else {
            result = new ArrayList<>(heroList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityListToDtoList(result));
    }


}
