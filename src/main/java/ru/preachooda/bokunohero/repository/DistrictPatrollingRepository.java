package ru.preachooda.bokunohero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface DistrictPatrollingRepository extends BaseEntityRepository<DistrictPatrolling> {

    @Query(nativeQuery = true, value = "select * from district_patrolling dp inner join " +
            "district_patrolling_heroes d on dp.id = d.district_patrolling_id where d.hero_id = hero_id")
    List<DistrictPatrolling> findAllByHero(@Param("heroId") Long heroId);

}
