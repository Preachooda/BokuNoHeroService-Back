package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.QualificationExam;
import ru.preachooda.bokunohero.repository.QualificationExamRepository;
import ru.preachooda.bokunoherocore.repository.BaseEntityRepository;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

import java.util.List;

@Service
public class QualificationExamService extends BaseEntityService<QualificationExam> {

    @Autowired
    private QualificationExamRepository qualificationExamRepository;

    @Override
    public BaseEntityRepository<QualificationExam> getRepository() {
        return qualificationExamRepository;
    }

    public List<QualificationExam> findAllByAcademy(Academy academy) {
        return qualificationExamRepository.findAllByAcademy(academy);
    }

    public List<QualificationExam> findAllByAcademyId(Long academyId) {
        Academy academy = new Academy();
        academy.setId(academyId);
        return qualificationExamRepository.findAllByAcademy(academy);
    }

}
