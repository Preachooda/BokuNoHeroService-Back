package ru.preachooda.bokunohero.repository;

import org.springframework.stereotype.Repository;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.QualificationExam;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;

import java.util.List;

@Repository
public interface QualificationExamRepository extends BaseEntityRepository<QualificationExam> {

    List<QualificationExam> findAllByAcademy(Academy academy);

}
