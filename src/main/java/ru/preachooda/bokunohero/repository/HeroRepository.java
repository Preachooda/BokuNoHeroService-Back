package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface HeroRepository extends BaseEntityRepository<Hero> {

    List<Hero> findByIdIn(List<Long> ids);

}
