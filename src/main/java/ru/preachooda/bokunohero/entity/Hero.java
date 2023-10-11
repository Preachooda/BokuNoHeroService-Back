package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

}
