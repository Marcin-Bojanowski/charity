package pl.coderslab.charity.validation.validators;

import pl.coderslab.charity.validation.validatorsClasses.AuthenticateValidator;
import pl.coderslab.charity.validation.validatorsClasses.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AuthenticateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authenticate {
    String message() default "Authenticate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
