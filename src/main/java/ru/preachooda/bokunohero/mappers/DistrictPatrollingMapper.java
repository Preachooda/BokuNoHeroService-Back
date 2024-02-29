package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.preachooda.bokunohero.dto.DistrictPatrollingDto;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.entity.DistrictPatrolling;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class DistrictPatrollingMapper extends BaseEntityMapper<DistrictPatrolling, DistrictPatrollingDto> {

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract Hero heroDtoToHero(HeroDto heroDto);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract HeroDto heroToHeroDto(Hero hero);
    
}
