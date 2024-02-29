package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.QuirkType;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

    @Column(name = "quirk")
    private String quirk;

    @Column(name = "quirk_type")
    @Enumerated(EnumType.STRING)
    private QuirkType quirkType;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "strength")
    private Integer strength;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "speed")
    private Integer speed;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "technique")
    private Integer technique;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "intelligence")
    private Integer intelligence;

    @NotNull
    @Min(0)
    @Max(5)
    @Column(name = "cooperation")
    private Integer cooperation;

}
