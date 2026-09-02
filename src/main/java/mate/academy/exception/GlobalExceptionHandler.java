package mate.academy.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //isbn exists already. isbn must be unique -> SQL Exception (.SQLIntegrityConstraintViolationException

    //notNull values -> SQL Exception (.SQLIntegrityConstraintViolationException)

    // Book wasnt found -> EntityNotFoundExceptrion

    // price not null








}
