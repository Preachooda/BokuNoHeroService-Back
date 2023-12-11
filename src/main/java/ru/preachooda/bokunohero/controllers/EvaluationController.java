package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.preachooda.bokunohero.dto.EvaluationDto;
import ru.preachooda.bokunohero.entity.Evaluation;
import ru.preachooda.bokunohero.mappers.EvaluationMapper;
import ru.preachooda.bokunohero.services.EvaluationService;
import ru.preachooda.bokunoherocore.utils.BaseUtils;
import ru.preachooda.bokunoherocore.validator.exceptions.IncorrectDataException;
import ru.preachooda.bokunoherocore.validator.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private EvaluationMapper evaluationMapper;

//    @GetMapping({"/{id}"})
//    public ResponseEntity<EvaluationDto> getById(@PathVariable("id") String id) throws IncorrectDataException, NotFoundException {
//        if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
//            throw new NotFoundException("Параметр '" + id + "' передан неверно");
//        } else {
//            Evaluation entity = evaluationService.find(BaseUtils.convertToLong(id));
//            return ResponseEntity.status(HttpStatus.OK).body(evaluationMapper.entityToDto(entity));
//        }
//    }

    @GetMapping
    public ResponseEntity<List<EvaluationDto>> getAll() {
        List<Evaluation> entityList = evaluationService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(evaluationMapper.entityListToDtoList(entityList));
    }

    @PostMapping
    public ResponseEntity<EvaluationDto> create(@RequestBody EvaluationDto dto) {
        Evaluation entity = evaluationService.create(evaluationMapper.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(evaluationMapper.entityToDto(entity));
    }

//    @PutMapping({"/{id}"})
//    public ResponseEntity<EvaluationDto> update(@PathVariable("id") String id, @RequestBody EvaluationDto dto) throws IncorrectDataException, NotFoundException {
//        if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр '" + id + "' передан неверно");
//        } else {
//            dto.setId(BaseUtils.convertToLong(id));
//            Evaluation entity = evaluationService.update(evaluationMapper.dtoToEntity(dto));
//            return ResponseEntity.status(HttpStatus.OK).body(evaluationMapper.entityToDto(entity));
//        }
//    }

//    @DeleteMapping({"/{id}"})
//    public ResponseEntity<EvaluationDto> delete(@PathVariable("id") String id) throws IncorrectDataException, NotFoundException {
//        if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
//            throw new NotFoundException("Параметр '" + id + "' передан неверно");
//        } else {
//            Evaluation entity = evaluationService.delete(BaseUtils.convertToLong(id));
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(evaluationMapper.entityToDto(entity));
//        }
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError)error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
