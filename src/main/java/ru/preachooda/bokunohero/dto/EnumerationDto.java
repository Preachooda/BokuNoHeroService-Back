package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EnumerationDto extends BaseDto {

    private String code;

}
