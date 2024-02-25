package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.TicketMediaFile;
import ru.preachooda.bokunohero.repository.TicketMediaFileRepository;

import java.util.List;

@Service
public class TicketMediaFileService {

    @Autowired
    private TicketMediaFileRepository ticketMediaFileRepository;

//    public Evaluation find(Long id) throws IncorrectDataException, NotFoundException {
//        if (id == null) {
//            throw new IncorrectDataException("Не передан идентификатор объекта");
//        } else {
//            Optional<Evaluation> optional = evaluationRepository.findById(id);
//            if (optional.isPresent()) {
//                return optional.get();
//            } else {
//                throw new NotFoundException(String.format("Не найден объект с идентификатором %d", id));
//            }
//        }
//    }

    public List<TicketMediaFile> findAll() {
        return (List<TicketMediaFile>) ticketMediaFileRepository.findAll();
    }

    public List<TicketMediaFile> findByTicketId(Long ticketId) {
        return ticketMediaFileRepository.findByTicketMediaKeyTicketId(ticketId);
    }

    public TicketMediaFile create(TicketMediaFile entity) {
        return ticketMediaFileRepository.save(entity);
    }

//    public Evaluation update(Evaluation object) throws IncorrectDataException, NotFoundException {
//        this.find(object.getId());
//        return (BaseEntity)this.getRepository().save(object);
//    }

//    public Evaluation delete(Long id) throws IncorrectDataException, NotFoundException {
//        Evaluation object = this.find(id);
//        this.getRepository().delete(object);
//        return object;
//    }

}
