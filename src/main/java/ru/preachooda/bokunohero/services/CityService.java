package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.City;
import ru.preachooda.bokunohero.repository.CityRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class CityService extends BaseEntityService<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public BaseEntityRepository<City> getRepository() {
        return cityRepository;
    }

}
