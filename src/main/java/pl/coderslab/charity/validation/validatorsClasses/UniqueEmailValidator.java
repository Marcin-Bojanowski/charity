package pl.coderslab.charity.validation.validatorsClasses;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.services.ValidationService;
import pl.coderslab.charity.validation.validators.UniqueEmail;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final ValidationService validationService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return validationService.isUniqueEmail(s);
    }
}
