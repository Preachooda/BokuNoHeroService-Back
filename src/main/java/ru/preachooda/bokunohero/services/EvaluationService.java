package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.repository.EvaluationRepository;

import java.util.List;

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
