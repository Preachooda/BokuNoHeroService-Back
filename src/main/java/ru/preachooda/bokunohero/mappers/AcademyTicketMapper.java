package ru.preachooda.bokunohero.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.preachooda.bokunohero.dto.AcademyDto;
import ru.preachooda.bokunohero.dto.AcademyTicketDto;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.AcademyTicket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class AcademyTicketMapper extends BaseEntityMapper<AcademyTicket, AcademyTicketDto> {

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract Academy academyDtoToAcademy(AcademyDto academyDto);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract AcademyDto academyToAcademyDto(Academy academy);

    @AfterMapping
    public void academyTicketToAcademyAcademyTicketDto(AcademyTicket academyTicket, @MappingTarget AcademyTicketDto academyTicketDto) {
        academyTicketDto.setUserId(academyTicket.getUser().getId());
    }

    @AfterMapping
    public void academyTicketDtoToAcademyTicket(AcademyTicketDto academyTicketDto, @MappingTarget AcademyTicket academyTicket) {
        // Устанавливаем юзера
        User user = new User();
        user.setId(academyTicketDto.getUserId());
        academyTicket.setUser(user);
    }
    
}
