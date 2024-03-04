package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "license_ticket")
public class LicenseTicket extends BaseEntity {

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @NotNull
    @Column(name = "age")
    private String heroName;

    @NotNull
    @Column(name = "printed_name")
    private String printedName;

    @NotNull
    @Column(name = "quirk")
    private String quirk;

    @NotNull
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @Column(name = "education_document_number")
    private String educationDocumentNumber;

}
