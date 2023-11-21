package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HeroDto extends BaseDto {

    private UserDto user;

}
