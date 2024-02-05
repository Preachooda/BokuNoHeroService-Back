package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.preachooda.bokunohero.dto.TicketDto;
import ru.preachooda.bokunohero.dto.UserDto;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class TicketMapper extends BaseEntityMapper<Ticket, TicketDto> {

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract User userDtoToUser(UserDto userDto);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract UserDto userToUserDto(User user);

}
