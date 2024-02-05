package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TicketDto extends BaseDto {

    private UserDto user;

    private String description;

    private String status;

    private Integer priority;

    private String latitude;

    private String longitude;

//    private List<HeroDto> heroes;

    private List<String> categories;

}
