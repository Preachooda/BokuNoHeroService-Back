package ru.preachooda.bokunohero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.AcademyTicket;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface AcademyTicketRepository extends BaseEntityRepository<AcademyTicket> {

    @Query(nativeQuery = true, value = "SELECT * from academy_ticket at where at.first_academy_id = :academyId or at.second_academy_id = :academyId or at.third_academy_id = :academyId")
    List<AcademyTicket> findAllByAcademiesId(@Param("academyId") Long academyId);

}
