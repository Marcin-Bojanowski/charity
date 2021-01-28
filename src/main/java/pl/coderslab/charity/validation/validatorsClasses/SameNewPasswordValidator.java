package pl.coderslab.charity.validation.validatorsClasses;

import pl.coderslab.charity.dtos.password.NewPasswordDTO;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.validation.validators.SamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SameNewPasswordValidator implements ConstraintValidator<SamePassword, NewPasswordDTO> {
    public boolean isValid(NewPasswordDTO newPasswordDTO, ConstraintValidatorContext context) {
        boolean valid = newPasswordDTO.getPassword().equals(newPasswordDTO.getRePassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("SamePassword")
                    .addPropertyNode("password").addConstraintViolation();
        }
        return valid;
    }
}
