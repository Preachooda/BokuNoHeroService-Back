package ru.preachooda.bokunohero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.preachooda.bokunohero.entity.BaseEntity;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity> extends CrudRepository<E, Long> {

    E findByLabel(String label);

}
