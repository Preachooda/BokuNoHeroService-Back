package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

@Repository
public interface RoleRepository extends BaseEntityRepository<Role> {

    Role findByCode(String code);

}
