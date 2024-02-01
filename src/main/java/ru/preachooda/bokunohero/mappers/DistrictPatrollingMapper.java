package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.DistrictPatrollingDto;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class DistrictPatrollingMapper extends BaseEntityMapper<DistrictPatrolling, DistrictPatrollingDto> {
}
