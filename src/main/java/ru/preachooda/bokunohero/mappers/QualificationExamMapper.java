package ru.preachooda.bokunohero.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.preachooda.bokunohero.dto.AcademyDto;
import ru.preachooda.bokunohero.dto.QualificationExamDto;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.QualificationExam;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.mappers.BaseMapper;

@Mapper(
        componentModel = "spring",
        uses = BaseMapper.class
)
public abstract class QualificationExamMapper extends BaseEntityMapper<QualificationExam, QualificationExamDto> {

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract Academy academyDtoToAcademy(AcademyDto academyDto);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = "dateTime")
    public abstract AcademyDto academyToAcademyDto(Academy academy);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = {"dateTime"})
    @Mapping(source = "startDateTime", target = "startDateTime", qualifiedByName = {"dateTime"})
    @Mapping(source = "endDateTime", target = "endDateTime", qualifiedByName = {"dateTime"})
    public abstract QualificationExam dtoToEntity(QualificationExamDto var1);

    @Mapping(source = "creationDate", target = "creationDate", qualifiedByName = {"dateTime"})
    @Mapping(source = "startDateTime", target = "startDateTime", qualifiedByName = {"dateTime"})
    @Mapping(source = "endDateTime", target = "endDateTime", qualifiedByName = {"dateTime"})
    public abstract QualificationExamDto entityToDto(QualificationExam var1);

    @AfterMapping
    public void qualificationExamToAcademyQualificationExamDto(QualificationExam qualificationExam, @MappingTarget QualificationExamDto qualificationExamDto) {
        qualificationExamDto.setAcademyId(qualificationExam.getAcademy().getId());

        // Академии
//        qualificationExamDto.setFirstAcademyId(qualificationExam.getFirstAcademy().getId());
//        qualificationExamDto.setSecondAcademyId(qualificationExam.getSecondAcademy().getId());
//        qualificationExamDto.setThirdAcademyId(qualificationExam.getThirdAcademy().getId());
    }

    @AfterMapping
    public void qualificationExamDtoToQualificationExam(QualificationExamDto qualificationExamDto, @MappingTarget QualificationExam qualificationExam) {
        // Устанавливаем академию
        Academy academy = new Academy();
        academy.setId(qualificationExamDto.getAcademyId());
        qualificationExam.setAcademy(academy);

        //  Академии
//        Academy firstAcademy = new Academy();
//        qualificationExamDto.setId(qualificationExamDto.getFirstAcademyId());
//        qualificationExam.setFirstAcademy(firstAcademy);
//
//        Academy secondAcademy = new Academy();
//        qualificationExamDto.setId(qualificationExamDto.getSecondAcademyId());
//        qualificationExam.setSecondAcademy(secondAcademy);
//
//        Academy thirdAcademy = new Academy();
//        qualificationExamDto.setId(qualificationExamDto.getThirdAcademyId());
//        qualificationExam.setThirdAcademy(thirdAcademy);
    }
    
}
