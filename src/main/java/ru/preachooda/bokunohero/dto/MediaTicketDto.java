package ru.preachooda.bokunohero.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class MediaTicketDto {

    private List<String> photosCodes = new ArrayList<>();

    private String videoCode;

    private String audioCode;

}
