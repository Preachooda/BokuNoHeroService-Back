package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.DistrictPatrollingDto;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.mappers.DistrictPatrollingMapper;
import ru.preachooda.bokunohero.services.DistrictPatrollingService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/district-patrollings")
public class DistrictPatrollingController extends BaseEntityController<DistrictPatrolling, DistrictPatrollingDto> {

    @Autowired
    private DistrictPatrollingService districtPatrollingService;

    @Autowired
    private DistrictPatrollingMapper districtPatrollingMapper;

    @Override
    public BaseEntityService<DistrictPatrolling> getService() {
        return districtPatrollingService;
    }

    @Override
    public BaseEntityMapper<DistrictPatrolling, DistrictPatrollingDto> getMapper() {
        return districtPatrollingMapper;
    }

    @GetMapping("/active/{heroId}")
    public ResponseEntity<DistrictPatrollingDto> findActiveByHeroId(@PathVariable("heroId") Long heroId) {
        return ResponseEntity.ok().body(getMapper().entityToDto(districtPatrollingService.findActiveByHeroId(heroId)));
    }

}
