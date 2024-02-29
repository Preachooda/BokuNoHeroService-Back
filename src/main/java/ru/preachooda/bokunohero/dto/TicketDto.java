package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TicketDto extends BaseDto {

    private Long userId;

    private String description;

    private String status;

    private Integer priority;

    private String ticketComplexity;

    private String latitude;

    private String longitude;

    private Integer score;

//    private List<HeroDto> heroes;

    private List<String> photosCodes = new ArrayList<>();

    private String videoCode;

    private String audioCode;

    private List<String> photosPaths = new ArrayList<>();

    private String videoPath;

    private String audioPath;

    private List<String> categories;

}
