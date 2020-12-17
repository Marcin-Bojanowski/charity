package pl.coderslab.charity.validation.validatorsClasses;

import pl.coderslab.charity.dtos.password.PasswordDTO;
import pl.coderslab.charity.validation.validators.SamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SamePasswordUpdateValidator implements ConstraintValidator<SamePassword, PasswordDTO> {
    @Override
    public void initialize(SamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(PasswordDTO password, ConstraintValidatorContext context) {
        boolean valid = password.getPassword().equals(password.getRePassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("SamePassword")
                    .addPropertyNode("password").addConstraintViolation();
        }
        return valid;
    }
}
