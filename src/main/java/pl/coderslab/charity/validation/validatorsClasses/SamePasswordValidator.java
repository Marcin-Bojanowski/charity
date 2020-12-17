package pl.coderslab.charity.validation.validatorsClasses;

import pl.coderslab.charity.dtos.password.PasswordDTO;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.validation.validators.SamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class SamePasswordValidator implements ConstraintValidator<SamePassword, NewUserDTO> {
   public void initialize(SamePassword constraint) {
   }


   public boolean isValid( NewUserDTO user, ConstraintValidatorContext context) {
      boolean valid = user.getPassword().equals(user.getRePassword());
      if (!valid) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate("SamePassword")
                 .addPropertyNode("password").addConstraintViolation();
      }
      return valid;
   }

}
