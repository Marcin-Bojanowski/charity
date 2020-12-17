package pl.coderslab.charity.validation.validatorsClasses;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.charity.utilities.LoggedUser;
import pl.coderslab.charity.validation.validators.Authenticate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Data
public class AuthenticateValidator implements ConstraintValidator<Authenticate, String> {

   private final PasswordEncoder passwordEncoder;
   private final LoggedUser loggedUser;

   public void initialize(Authenticate constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return passwordEncoder.matches(obj,loggedUser.getLoggedUser().getPassword());
   }
}
