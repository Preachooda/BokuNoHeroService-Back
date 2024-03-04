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
public class LicenseTicketDto extends BaseDto {

    private String status;

    private String heroName;

    private String printedName;

    private String quirk;

    private String birthDate;

    private String educationDocumentNumber;

}
