package ru.preachooda.bokunohero.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class QualificationExamDto extends BaseDto {

    private String status;

    private Long academyId;

    private HeroDto hero;

    private HeroDto opponent;

    private HeroDto instructor;

    private String startDateTime;

    private String endDateTime;

}
