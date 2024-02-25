package ru.preachooda.bokunohero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.TicketMediaFile;
import ru.preachooda.bokunohero.entity.composite.TicketMediaKey;

import java.util.List;

@Repository
public interface TicketMediaFileRepository extends CrudRepository<TicketMediaFile, TicketMediaKey> {

    List<TicketMediaFile> findByTicketMediaKeyTicketId(Long ticketId);

}
