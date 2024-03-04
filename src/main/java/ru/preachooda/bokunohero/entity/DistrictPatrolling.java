package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.PatrolStatus;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "district_patrolling")
public class DistrictPatrolling extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PatrolStatus status = PatrolStatus.PENDING;

    @NotNull
    @Column(name = "district")
    private String district;

    @Column(name = "description")
    private String description;

    @Column(name = "scheduled_start")
    private Date scheduledStart;

    @Column(name = "scheduled_end")
    private Date scheduledEnd;

    @Column(name = "actual_start")
    private Date actualStart;

    @Column(name = "actual_end")
    private Date actualEnd;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "district_patrolling_heroes",
            joinColumns = @JoinColumn(name = "district_patrolling_id"),
            inverseJoinColumns = @JoinColumn(name = "hero_id"))
    private List<Hero> heroes;

}
