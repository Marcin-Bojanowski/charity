package pl.coderslab.charity.validation.validatorsClasses;

import lombok.Data;
import pl.coderslab.charity.dtos.email.EmailDTO;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.validation.validators.EmailNotExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;
@Data
public class EmailNotExistValidator implements ConstraintValidator<EmailNotExist,String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userRepository.existsByEmail(email);
    }
}
