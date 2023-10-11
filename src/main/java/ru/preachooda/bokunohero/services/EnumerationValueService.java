package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.EnumerationValue;
import ru.preachooda.bokunohero.repository.BaseEntityRepository;
import ru.preachooda.bokunohero.repository.EnumerationValueRepository;

@Service
public class EnumerationValueService extends BaseEntityService<EnumerationValue> {

    @Autowired
    private EnumerationValueRepository roleRepository;

    @Override
    public BaseEntityRepository<EnumerationValue> getRepository() {
        return roleRepository;
    }

}
