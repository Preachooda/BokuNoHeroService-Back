package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.EnumerationDto;
import ru.preachooda.bokunohero.dto.RoleDto;
import ru.preachooda.bokunohero.entity.Enumeration;
import ru.preachooda.bokunohero.entity.Role;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class EnumerationMapper extends BaseEntityMapper<Enumeration, EnumerationDto> {
}
