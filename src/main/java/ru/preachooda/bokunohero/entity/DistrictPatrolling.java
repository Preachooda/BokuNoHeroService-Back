package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "district_patrolling")
public class DistrictPatrolling extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "district_patrolling_heroes",
            joinColumns = @JoinColumn(name = "district_patrolling_id"),
            inverseJoinColumns = @JoinColumn(name = "hero_id"))
    private List<Hero> heroes;

}
