package com.fasttrackit.JavaEncyclopediaProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NullFieldException.class)
    public ErrorResponse handleNullFieldException(NullFieldException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}

record ErrorResponse(String message) {

}
