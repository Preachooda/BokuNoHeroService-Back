package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.preachooda.bokunohero.dto.EvaluationDto;
import ru.preachooda.bokunohero.dto.RoleDto;
import ru.preachooda.bokunohero.dto.TicketHeroKeyDto;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class EvaluationMapper {

    public abstract TicketHeroKey ticketHeroKeyDtoToTickerHeroKey(TicketHeroKeyDto ticketHeroKeyDto);

    public abstract TicketHeroKeyDto ticketHeroKeyToTicketHeroKeyDto(TicketHeroKey ticketHeroKey);

    public abstract Evaluation dtoToEntity(EvaluationDto var1);

    public abstract EvaluationDto entityToDto(Evaluation var1);

    public abstract List<EvaluationDto> entityListToDtoList(List<Evaluation> var1);

    public abstract List<Evaluation> dtoListToEntityList(List<EvaluationDto> var1);

}
