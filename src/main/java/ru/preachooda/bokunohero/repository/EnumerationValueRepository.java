package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Enumeration;
import ru.preachooda.bokunohero.entity.EnumerationValue;

import java.util.List;

@Repository
public interface EnumerationValueRepository extends BaseEntityRepository<EnumerationValue>{

    EnumerationValue findByCode(String code);

    List<EnumerationValue> findAllByEnumeration(Enumeration enumeration);
}
