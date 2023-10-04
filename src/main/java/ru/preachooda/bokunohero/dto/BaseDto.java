package ru.preachooda.bokunohero.dto;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDto {

    private Long id;

    private String label;

    private Date creationDate;

}
