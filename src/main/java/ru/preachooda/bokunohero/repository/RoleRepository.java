package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Role;

@Repository
public interface RoleRepository extends BaseEntityRepository<Role>{

    Role findByCode(String code);

}
