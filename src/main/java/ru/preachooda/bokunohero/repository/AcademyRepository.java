package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

@Repository
public interface AcademyRepository extends BaseEntityRepository<Academy> {

    Academy findByHead(User user);

}
