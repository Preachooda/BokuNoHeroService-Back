package ru.preachooda.bokunohero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.entity.composite.TicketHeroKey;

import java.util.List;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, TicketHeroKey> {

    List<Evaluation> findByTicketHeroKeyTicketId(Long ticketId);

}
