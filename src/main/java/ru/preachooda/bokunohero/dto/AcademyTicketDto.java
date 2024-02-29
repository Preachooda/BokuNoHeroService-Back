package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunoherocore.dto.BaseDto;

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

    private Academy thirdAcademy;

}
