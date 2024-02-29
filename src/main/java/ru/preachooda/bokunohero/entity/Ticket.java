package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.dto.enumeration.TicketCategory;
import ru.preachooda.bokunohero.dto.enumeration.TicketComplexity;
import ru.preachooda.bokunohero.dto.enumeration.TicketPriority;
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
    @Enumerated(EnumType.ORDINAL)
    private TicketPriority priority = TicketPriority.ONE;

    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private TicketComplexity ticketComplexity = TicketComplexity.EASY;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "score")
    private Integer score;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ticket_photos_paths", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "photo_path", nullable = false)
    private List<String> photosPaths;

    @Column(name = "video_path")
    private String videoPath;

    @Column(name = "audio_path")
    private String audioPath;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "hero_id", referencedColumnName = "id")
//    private List<Hero> heroes;

    @ElementCollection(targetClass = TicketCategory.class)
    @CollectionTable(name = "ticket_categories", joinColumns = @JoinColumn(name = "ticket_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_category")
    private List<TicketCategory> categories;

}
