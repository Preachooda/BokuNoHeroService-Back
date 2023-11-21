package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleDto extends BaseDto {

    private String code;

}
