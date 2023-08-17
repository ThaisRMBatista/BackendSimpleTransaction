package com.transferenciasimplificada.infra;

import com.transferenciasimplificada.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionDTO duplicateUserException = new ExceptionDTO("400", "Usuário já cadastrado");
        return ResponseEntity.badRequest().body(duplicateUserException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity threatGeneralException(Exception exception) {
        ExceptionDTO generalException = new ExceptionDTO("500", exception.getMessage());
        return ResponseEntity.internalServerError().body(generalException);
    }
}
