package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.entity.Hero;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class HeroMapper extends BaseEntityMapper<Hero, HeroDto> {
}