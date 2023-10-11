package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.EnumerationValueDto;
import ru.preachooda.bokunohero.entity.EnumerationValue;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class EnumerationValueMapper extends BaseEntityMapper<EnumerationValue, EnumerationValueDto> {
}
