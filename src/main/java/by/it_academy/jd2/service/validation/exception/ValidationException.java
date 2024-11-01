package by.it_academy.jd2.service.validation.exception;

import by.it_academy.jd2.service.validation.Error;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
