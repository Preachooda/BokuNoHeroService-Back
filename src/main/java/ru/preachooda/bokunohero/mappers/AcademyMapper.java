package ru.preachooda.bokunohero.mappers;

import org.mapstruct.Mapper;
import ru.preachooda.bokunohero.dto.AcademyDto;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class AcademyMapper extends BaseEntityMapper<Academy, AcademyDto> {
}
