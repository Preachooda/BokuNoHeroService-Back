package ru.preachooda.bokunohero.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HeroDto extends BaseDto {

    private Long userId;

    private String quirk;

    private String quirkType;

    private Integer strength;

    private Integer speed;

    private Integer technique;

    private Integer intelligence;

    private Integer cooperation;

    private List<DistrictPatrollingDto> districtPatrollingDtoList;

}
