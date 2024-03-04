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
public class AcademyTicketDto extends BaseDto {

    private Long userId;

    private String status;

    private String printedName;

    private Integer age;

    private String quirk;

    private String educationDocumentNumber;

    private String message;

    private AcademyDto firstAcademy;

    private AcademyDto secondAcademy;

    private AcademyDto thirdAcademy;

}
