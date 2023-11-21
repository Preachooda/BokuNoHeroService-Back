package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.preachooda.bokunohero.dto.RoleDto;
import ru.preachooda.bokunohero.dto.UserDto;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class UserMapper extends BaseEntityMapper<User, UserDto> {

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract Role roleDtoToRole(RoleDto roleDto);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract RoleDto roleToRoleDto(Role role);

}
