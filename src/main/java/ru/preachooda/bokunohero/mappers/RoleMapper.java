package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.RoleDto;
import ru.preachooda.bokunohero.entity.Role;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class RoleMapper extends BaseEntityMapper<Role, RoleDto> {
}
