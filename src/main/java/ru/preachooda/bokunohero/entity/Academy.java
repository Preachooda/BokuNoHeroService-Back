package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "academy")
public class Academy extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    private User head;

    @NotNull
    @Column(name = "address")
    private String address;

    @Column(name = "motto")
    private String motto;

}
