package ru.preachooda.bokunohero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.dto.BaseDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TicketDto extends BaseDto {

    private Long userId;

    private String description;

    private String status;

    private Integer priority = 0;

    private String latitude;

    private String longitude;

    private Integer score;

//    private List<HeroDto> heroes;

    private String videoCode;

    private String imageCode;

    private String audioCode;

    private List<String> categories;

}
