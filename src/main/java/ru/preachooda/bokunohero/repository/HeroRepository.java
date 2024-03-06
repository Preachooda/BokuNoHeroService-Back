package ru.preachooda.bokunohero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface HeroRepository extends BaseEntityRepository<Hero> {

    List<Hero> findByIdIn(List<Long> ids);

    @Query("FROM Hero h " +
                        "LEFT JOIN Evaluation e ON e.ticketHeroKey.heroId = h.id " +
                        "LEFT JOIN Ticket t ON e.ticketHeroKey.ticketId = t.id " +
                        "WHERE t.status NOT IN (:statusList) OR e.ticketHeroKey.heroId is null")
    List<Hero> findAvailableHeroes(@Param("statusList") List<ActivityStatus> statusList);

}
