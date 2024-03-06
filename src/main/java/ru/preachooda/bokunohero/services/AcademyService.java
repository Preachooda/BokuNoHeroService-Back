package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.repository.AcademyRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@Service
public class AcademyService extends BaseEntityService<Academy> {

    @Autowired
    private AcademyRepository academyRepository;

    @Override
    public BaseEntityRepository<Academy> getRepository() {
        return academyRepository;
    }

    public Academy findByHead(User user) { return academyRepository.findByHead(user); }

}
