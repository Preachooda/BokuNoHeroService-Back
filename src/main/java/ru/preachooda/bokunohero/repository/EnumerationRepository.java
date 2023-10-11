package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Enumeration;
import ru.preachooda.bokunohero.entity.Role;

@Repository
public interface EnumerationRepository extends BaseEntityRepository<Enumeration>{

    Enumeration findByCode(String code);

}
