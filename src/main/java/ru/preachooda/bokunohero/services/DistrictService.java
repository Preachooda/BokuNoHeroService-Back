package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.District;
import ru.preachooda.bokunohero.repository.DistrictRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class DistrictService extends BaseEntityService<District> {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public BaseEntityRepository<District> getRepository() {
        return districtRepository;
    }

}
