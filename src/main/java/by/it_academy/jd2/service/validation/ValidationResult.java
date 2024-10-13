package by.it_academy.jd2.service.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private final List<Error> errors = new ArrayList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public boolean checkErrorEmpty() {
        return errors.isEmpty();
    }
}
