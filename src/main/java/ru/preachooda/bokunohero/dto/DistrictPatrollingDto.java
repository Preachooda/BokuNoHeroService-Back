package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DistrictPatrollingDto extends BaseDto {

    private String status;

    private String district;

    private String description;

    private String scheduledStart;

    private String scheduledEnd;

    private String actualStart;

    private String actualEnd;

    private List<HeroDto> heroes;

}
