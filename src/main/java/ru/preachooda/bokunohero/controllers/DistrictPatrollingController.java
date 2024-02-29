package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

}
