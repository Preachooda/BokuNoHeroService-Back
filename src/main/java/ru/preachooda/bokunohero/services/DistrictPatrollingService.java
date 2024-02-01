package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.repository.DistrictPatrollingRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class DistrictPatrollingService extends BaseEntityService<DistrictPatrolling> {

    @Autowired
    private DistrictPatrollingRepository districtPatrollingRepository;

    @Override
    public BaseEntityRepository<DistrictPatrolling> getRepository() {
        return districtPatrollingRepository;
    }

}
