package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.QuirkType;
import ru.preachooda.bokunohero.dto.enumeration.Tier;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "hero")
public class Hero extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

    @NotNull
    @Column(name = "quirk")
    private String quirk;

    @NotNull
    @Column(name = "quirk_type")
    @Enumerated(EnumType.STRING)
    private QuirkType quirkType;

    @NotNull
    @Column(name = "skill_by_quirk")
    @Enumerated(EnumType.STRING)
    private Tier skillByQuirk = Tier.F;

//    @NotNull
    @Column(name = "ranking_position", unique = true)
    private Integer rankingPosition = -1;

    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "strength")
    private Integer strength;

    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "speed")
    private Integer speed;

    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "technique")
    private Integer technique;

    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "intelligence")
    private Integer intelligence;

    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "cooperation")
    private Integer cooperation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "district_patrolling_heroes",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "district_patrolling_id"))
    private List<DistrictPatrolling> districtPatrollingList;

}
