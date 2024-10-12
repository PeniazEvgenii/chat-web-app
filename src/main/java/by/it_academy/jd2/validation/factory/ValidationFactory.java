package by.it_academy.jd2.validation.factory;

import by.it_academy.jd2.validation.ValidationForm;
import by.it_academy.jd2.validation.api.IValidate;

public class ValidationFormFactory {
    private static final IValidate INSTANCE = new ValidationForm();

    private ValidationFormFactory() {}

    public static IValidate getInstance() {
        return INSTANCE;
    }

}
