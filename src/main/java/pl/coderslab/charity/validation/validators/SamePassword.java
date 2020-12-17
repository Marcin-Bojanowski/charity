package pl.coderslab.charity.validation.validators;

import pl.coderslab.charity.validation.validatorsClasses.PasswordValidator;
import pl.coderslab.charity.validation.validatorsClasses.SamePasswordUpdateValidator;
import pl.coderslab.charity.validation.validatorsClasses.SamePasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {SamePasswordValidator.class, SamePasswordUpdateValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SamePassword {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
