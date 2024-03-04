package ru.preachooda.bokunohero.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class HeroMapper extends BaseEntityMapper<Hero, HeroDto> {

    @AfterMapping
    public void ticketToHeroDto(Ticket ticket, @MappingTarget HeroDto heroDto) {
        heroDto.setUserId(ticket.getUser().getId());
    }

    @AfterMapping
    public void heroDtoToTicket(HeroDto heroDto, @MappingTarget Ticket ticket) {
        // Устанавливаем юзера
        User user = new User();
        user.setId(heroDto.getUserId());
        ticket.setUser(user);
    }
    
}