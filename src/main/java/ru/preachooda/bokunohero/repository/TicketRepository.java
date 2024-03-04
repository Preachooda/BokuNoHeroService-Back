package ru.preachooda.bokunohero.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Ticket;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface TicketRepository extends BaseEntityRepository<Ticket> {

    List<Ticket> findAllByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * FROM ticket t INNER JOIN evaluation e ON e.ticket_id = t.id " +
            "WHERE e.hero_id = :heroId and t.status = :status")
    List<Ticket> findAllByHeroIdAndStatus(@Param("heroId") Long heroId, @Param("status") String status);

}
