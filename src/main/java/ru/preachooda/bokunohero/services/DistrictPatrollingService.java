package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.dto.enumeration.PatrolStatus;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.repository.DistrictPatrollingRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;
import java.util.Objects;

@Service
public class DistrictPatrollingService extends BaseEntityService<DistrictPatrolling> {

    @Autowired
    private DistrictPatrollingRepository districtPatrollingRepository;

    @Override
    public BaseEntityRepository<DistrictPatrolling> getRepository() {
        return districtPatrollingRepository;
    }

    public DistrictPatrolling findActiveByHeroId(Long heroId) {
        List<DistrictPatrolling> districtPatrollingList = districtPatrollingRepository.findAllByHero(heroId);

        return districtPatrollingList.stream().filter(dp -> Objects.equals(dp.getStatus(), PatrolStatus.STARTED) ||
                Objects.equals(dp.getStatus(), PatrolStatus.PENDING)).findFirst().orElse(null);
    }

}
