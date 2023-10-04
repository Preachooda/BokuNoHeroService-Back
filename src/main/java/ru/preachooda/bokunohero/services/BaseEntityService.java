package ru.preachooda.bokunohero.services;


import ru.preachooda.bokunohero.entity.BaseEntity;
import ru.preachooda.bokunohero.repository.BaseEntityRepository;
import ru.preachooda.bokunohero.validator.exceptions.IncorrectDataException;
import ru.preachooda.bokunohero.validator.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseEntityService<E extends BaseEntity> {

    public abstract BaseEntityRepository<E> getRepository();

    public E find(Long id) throws IncorrectDataException, NotFoundException {
        if (id == null) {
            throw new IncorrectDataException("Не передан идентификатор объекта");
        }
        Optional<E> optional = this.getRepository().findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundException(String.format("Не найден объект с идентификатором %d", id));
        }
    }

    public List<E> findAll() {
        return (List<E>) this.getRepository().findAll();
    }

    public E create(E object) {
        object = beforeCreate(object);
        if (object.getCreationDate() == null)
            object.setCreationDate(new Date());
        return this.getRepository().save(object);
    }

    public E beforeCreate(E object) {
        return object;
    }

    public E update(E object) throws IncorrectDataException, NotFoundException {
        E dbObject = this.find(object.getId());
        return this.getRepository().save(object);
    }

    public E delete(Long id) throws IncorrectDataException, NotFoundException {
        E object = this.find(id);
        this.getRepository().delete(object);
        return object;
    }

}
