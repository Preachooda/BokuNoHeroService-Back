package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.DistrictDto;
import ru.preachooda.bokunohero.entity.District;
import ru.preachooda.bokunohero.mappers.DistrictMapper;
import ru.preachooda.bokunohero.services.DistrictService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseEntityController<District, DistrictDto> {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public BaseEntityService<District> getService() {
        return districtService;
    }

    @Override
    public BaseEntityMapper<District, DistrictDto> getMapper() {
        return districtMapper;
    }

}
