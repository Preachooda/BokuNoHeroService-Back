package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.repository.HeroRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;

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

}
