package ru.preachooda.bokunohero.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "enumerationvalues")
public class EnumerationValue extends BaseEntity {

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "enumeration_id", referencedColumnName = "id")
    private Enumeration enumeration;

}
