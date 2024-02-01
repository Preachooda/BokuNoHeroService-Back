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
    private ActivityStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "hero_id", referencedColumnName = "id")
    private List<Hero> heroes;

}
