package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AcademyDto extends BaseDto {

    private Long headId;

    private String address;

    private String motto;

}
