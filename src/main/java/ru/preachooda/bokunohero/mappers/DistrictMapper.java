package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.DistrictDto;
import ru.preachooda.bokunohero.entity.District;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class DistrictMapper extends BaseEntityMapper<District, DistrictDto> {
}
