package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.repository.RoleRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class RoleService extends BaseEntityService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseEntityRepository<Role> getRepository() {
        return roleRepository;
    }
}
