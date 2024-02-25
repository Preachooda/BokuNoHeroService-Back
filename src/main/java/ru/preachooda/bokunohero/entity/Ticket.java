package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.dto.enumeration.TicketCategory;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

//    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @Column(name = "priority")
    @Min(value = 0, message = "The prioriry mustn't be less than 0")
    private Integer priority = 0;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "score")
    private Integer score;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "hero_id", referencedColumnName = "id")
//    private List<Hero> heroes;

    @ElementCollection(targetClass = TicketCategory.class)
    @CollectionTable(name = "ticket_categories", joinColumns = @JoinColumn(name = "ticket_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_category")
    private List<TicketCategory> categories;

    // TODO: 20.02.2024 photoPaths 

}
