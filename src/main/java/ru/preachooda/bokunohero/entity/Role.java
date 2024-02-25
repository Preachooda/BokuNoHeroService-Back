package ru.preachooda.bokunohero.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.preachooda.bokunoherocore.entity.BaseEntity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "code", unique = true)
    private String code;

    @Override
    public String getAuthority() {
        return code;
    }
}
