package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.dto.enumeration.PatrolStatus;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.repository.HeroRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HeroService extends BaseEntityService<Hero> {

    @Autowired
    private HeroRepository heroRepository;

    @Override
    public BaseEntityRepository<Hero> getRepository() {
        return heroRepository;
    }

    public List<Hero> findAllByIds(List<Long> idList) {
        return heroRepository.findByIdIn(idList);
    }

    public List<Hero> findFreeHeroes() {
        List<Hero> heroList = heroRepository.findAvailableHeroes(List.of(ActivityStatus.IN_WORK, ActivityStatus.ASSIGNED, ActivityStatus.EVALUATION));
        return heroList.stream().filter(Objects::nonNull).filter(hero -> hero.getDistrictPatrollingList() == null
                || hero.getDistrictPatrollingList().isEmpty()
                || !List.of(PatrolStatus.STARTED, PatrolStatus.PENDING).containsAll(
                        hero.getDistrictPatrollingList()
                                .stream()
                                .map(DistrictPatrolling::getStatus)
                                .distinct()
                                .toList()))
                .collect(Collectors.toList());
    }

}
