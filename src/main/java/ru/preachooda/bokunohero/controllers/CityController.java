package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.CityDto;
import ru.preachooda.bokunohero.entity.City;
import ru.preachooda.bokunohero.mappers.CityMapper;
import ru.preachooda.bokunohero.services.CityService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/cities")
public class CityController extends BaseEntityController<City, CityDto> {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public BaseEntityService<City> getService() {
        return cityService;
    }

    @Override
    public BaseEntityMapper<City, CityDto> getMapper() {
        return cityMapper;
    }

}
