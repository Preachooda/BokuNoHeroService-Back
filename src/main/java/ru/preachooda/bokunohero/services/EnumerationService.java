package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Enumeration;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.repository.BaseEntityRepository;
import ru.preachooda.bokunohero.repository.EnumerationRepository;
import ru.preachooda.bokunohero.repository.RoleRepository;

@Service
public class EnumerationService extends BaseEntityService<Enumeration> {

    @Autowired
    private EnumerationRepository roleRepository;

    @Override
    public BaseEntityRepository<Enumeration> getRepository() {
        return roleRepository;
    }
}
