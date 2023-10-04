package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapping;
import ru.preachooda.bokunohero.dto.BaseDto;
import ru.preachooda.bokunohero.entity.BaseEntity;

import java.util.List;

public abstract class BaseEntityMapper<E extends BaseEntity, D extends BaseDto> {

    @Mapping(source = "initDate", target = "initDate", qualifiedByName = "dateTime")
    public abstract E dtoToEntity(D dto);

    @Mapping(source = "initDate", target = "initDate", qualifiedByName = "dateTime")
    public abstract D entityToDto(E entity);

    public abstract List<D> entityListToDtoList(List<E> entityList);

    public abstract List<E> dtoListToEntityList(List<D> dtoList);

}