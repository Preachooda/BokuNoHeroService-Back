package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.LicenseTicketDto;
import ru.preachooda.bokunohero.entity.LicenseTicket;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class LicenseTicketMapper extends BaseEntityMapper<LicenseTicket, LicenseTicketDto> {

}
