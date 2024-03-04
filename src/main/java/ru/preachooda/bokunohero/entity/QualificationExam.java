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
@Table(name = "qualification_exam")
public class QualificationExam extends BaseEntity {

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academy_id", referencedColumnName = "id")
    private Academy academy;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hero_id", referencedColumnName="id")
    private Hero hero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "opponent_id", referencedColumnName="id")
    private Hero opponent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName="id")
    private Hero instructor;

    @Column(name = "start_date_time")
    private Date startDateTime;

    @Column(name = "end_date_time")
    private Date endDateTime;

}
