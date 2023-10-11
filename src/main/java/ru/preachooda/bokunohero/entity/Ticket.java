package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "ticket_status", referencedColumnName="id")
    private EnumerationValue status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "hero_id", referencedColumnName = "id")
    private List<Hero> heroes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<EnumerationValue> categories;

}
