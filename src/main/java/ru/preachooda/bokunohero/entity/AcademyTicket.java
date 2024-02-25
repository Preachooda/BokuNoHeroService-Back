package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
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
@Table(name = "academy_ticket")
public class AcademyTicket extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthday")
    private Date birthDay;

    @ManyToOne
    @JoinColumn(name = "first_academy_id", referencedColumnName = "id")
    private Academy firstAcademy;

    @ManyToOne
    @JoinColumn(name = "second_academy_id", referencedColumnName = "id")
    private Academy secondAcademy;

    @ManyToOne
    @JoinColumn(name = "third_academy_id", referencedColumnName = "id")
    private Academy thirdAcademy;

}
