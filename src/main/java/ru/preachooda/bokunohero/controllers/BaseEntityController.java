package ru.preachooda.bokunohero.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.preachooda.bokunohero.dto.BaseDto;
import ru.preachooda.bokunohero.entity.BaseEntity;
import ru.preachooda.bokunohero.mappers.BaseEntityMapper;
import ru.preachooda.bokunohero.services.BaseEntityService;
import ru.preachooda.bokunohero.utils.BaseUtils;
import ru.preachooda.bokunohero.validator.exceptions.IncorrectDataException;
import ru.preachooda.bokunohero.validator.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseEntityController<E extends BaseEntity, D extends BaseDto> {

   public abstract BaseEntityService<E> getService();

   public abstract BaseEntityMapper<E, D> getMapper();

   @GetMapping("/{id}")
   public ResponseEntity<D> getById(@PathVariable("id") String id) throws IncorrectDataException, NotFoundException {
      if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
         throw new NotFoundException("Параметр '" + id + "' передан неверно");
      }
      E entity = this.getService().find(BaseUtils.convertToLong(id));
      return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityToDto(entity));
   }

   @GetMapping
   public ResponseEntity<List<D>> getAll() {
      List<E> entityList = this.getService().findAll();
      return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityListToDtoList(entityList));
   }

   @PostMapping
   public ResponseEntity<D> create(@RequestBody D dto) {
      E entity = this.getService().create(this.getMapper().dtoToEntity(dto));
      return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityToDto(entity));
   }

   @PutMapping("/{id}")
   public ResponseEntity<D> update(@PathVariable("id") String id, @RequestBody D dto) throws IncorrectDataException, NotFoundException {
      if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Параметр '" + id + "' передан неверно");
      }
      dto.setId(BaseUtils.convertToLong(id));
      E entity = this.getService().update(this.getMapper().dtoToEntity(dto));
      return ResponseEntity.status(HttpStatus.OK).body(this.getMapper().entityToDto(entity));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<D> delete(@PathVariable("id") String id) throws IncorrectDataException, NotFoundException {
      if (id != null && !id.isEmpty() && BaseUtils.convertToLong(id) == null) {
         throw new NotFoundException("Параметр '" + id + "' передан неверно");
      }
      E entity = this.getService().delete(BaseUtils.convertToLong(id));
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.getMapper().entityToDto(entity));
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });
      return errors;
   }

}
