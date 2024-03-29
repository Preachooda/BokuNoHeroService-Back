package ru.preachooda.bokunohero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.dto.enumeration.ActivityStatus;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface TicketRepository extends BaseEntityRepository<Ticket> {

    List<Ticket> findAllByUser(User user);

    @Query("FROM Ticket t INNER JOIN Evaluation e ON e.ticketHeroKey.heroId = t.id " +
             "WHERE e.ticketHeroKey.heroId = :heroId and t.status = :status")
    List<Ticket> findAllByHeroIdAndStatus(@Param("heroId") Long heroId, @Param("status") ActivityStatus status);

    @Query("FROM Ticket t INNER JOIN Evaluation e ON e.ticketHeroKey.ticketId = t.id " +
            "WHERE e.ticketHeroKey.heroId = :heroId and t.status in (:statusList)")
    List<Ticket> findAllByHeroIdAndStatusIn(@Param("heroId") Long heroId, @Param("statusList") List<ActivityStatus> statusList);

}
