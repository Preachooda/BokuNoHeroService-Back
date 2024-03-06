package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.repository.EvaluationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

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

    public List<Evaluation> findAll() {
        return (List) evaluationRepository.findAll();
    }

    public List<Evaluation> findByTicketId(Long ticketId) {
        return evaluationRepository.findByTicketHeroKeyTicketId(ticketId);
    }

    /**
     * Метод для поиска мапы ID Героя - Список его оценок по списку идентификаторов героев
     * @param heroIdList список идентификаторов героев
     * @return           мапа ID Героя - Список его оценок
     */
    public Map<Long, List<Evaluation>> findAllByHeroesId(List<Long> heroIdList) {
        Map<Long, List<Evaluation>> result = new HashMap<>();
        for (Long heroId : heroIdList.stream().filter(Objects::nonNull).toList()) {
            List<Evaluation> evaluationList = evaluationRepository.findByTicketHeroKeyHeroId(heroId);
            result.put(heroId, evaluationList);
        }

        return result;
    }

    /**
     * Метод для поиска оценок героя по его идентификатору
     * @param heroId идентификатор героя
     * @return       список оценок героя
     */
    public List<Evaluation> findAllByHeroeId(Long heroId) {
        return evaluationRepository.findByTicketHeroKeyHeroId(heroId);
    }

    public Evaluation create(Evaluation object) {
        object = this.beforeCreate(object);

        return evaluationRepository.save(object);
    }

    public Evaluation beforeCreate(Evaluation object) {
        return object;
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
