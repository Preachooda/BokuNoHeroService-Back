package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;
import ru.preachooda.bokunoherocore.dto.EnumerationValueDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TicketDto extends BaseDto {

    private UserDto userDto;

    private String description;

    private EnumerationValueDto status;

    private Integer priority;

    private String latitude;

    private String longitude;

    private List<HeroDto> heroes;

    private List<EnumerationValueDto> categories;

}
