package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.QualificationExamDto;
import ru.preachooda.bokunohero.entity.QualificationExam;
import ru.preachooda.bokunohero.mappers.QualificationExamMapper;
import ru.preachooda.bokunohero.services.QualificationExamService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/qualification-exams")
public class QualificationExamController extends BaseEntityController<QualificationExam, QualificationExamDto> {

    @Autowired
    private QualificationExamService qualificationExamService;

    @Autowired
    private QualificationExamMapper qualificationExamMapper;

    @Override
    public BaseEntityService<QualificationExam> getService() {
        return qualificationExamService;
    }

    @Override
    public BaseEntityMapper<QualificationExam, QualificationExamDto> getMapper() {
        return qualificationExamMapper;
    }

}
