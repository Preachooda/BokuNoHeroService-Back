package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunohero.dto.HeroDto;
import ru.preachooda.bokunohero.dto.enumeration.*;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status = ActivityStatus.CREATED;

    @NotNull
    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private TicketPriority priority = TicketPriority.ONE;

    @NotNull
    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private TicketComplexity ticketComplexity = TicketComplexity.VERY_EASY;

    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;

    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;

    @NotNull
    @Column(name = "rate")
    @Enumerated(EnumType.ORDINAL)
    private Rate rate = Rate.NOT_RATED;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ticket_photos_paths", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "photo_path", nullable = false)
    private List<String> photosPaths;

    @Column(name = "video_path")
    private String videoPath;

    @Column(name = "audio_path")
    private String audioPath;

    @ElementCollection(targetClass = TicketCategory.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ticket_categories", joinColumns = @JoinColumn(name = "ticket_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_category")
    private List<TicketCategory> categories;

    @Transient
    private List<HeroDto> heroes;

    @Transient
    private Map<Long, Integer> heroRate;

}
