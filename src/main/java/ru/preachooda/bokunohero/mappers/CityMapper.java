package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.CityDto;
import ru.preachooda.bokunohero.entity.City;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class CityMapper extends BaseEntityMapper<City, CityDto> {
}
