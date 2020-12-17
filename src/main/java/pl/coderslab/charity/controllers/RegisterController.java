package pl.coderslab.charity.controllers;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.exceptions.TokenExpiredException;
import pl.coderslab.charity.services.RegistrationService;
import pl.coderslab.charity.services.VerificationTokenService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    private final RegistrationService registrationService;
private final VerificationTokenService verificationTokenService;

    @GetMapping
    public String registrationForm(Model model){
        model.addAttribute("newUserDTO",new NewUserDTO());
        return "register";
    }

    @PostMapping
    public String register(@Valid NewUserDTO newUserDTO, BindingResult result, Model model){
        if (result.hasErrors()){
            log.info(result.toString());
            return "register";
        }
        try {
            registrationService.registerUser(newUserDTO);
        } catch (ConstraintViolationException exception){
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                Path propertyPath =violation.getPropertyPath();
                String property = Iterables.getLast(propertyPath).toString();
                result.rejectValue(property,message);
            }
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/confirmRegistration/{token}")
    public String activateAccount(@PathVariable String token, Model model){
        try {
            verificationTokenService.activateAccount(token);
        } catch (ElementNotFoundException | TokenExpiredException exception){
            model.addAttribute("error",exception.getMessage());
            return "confirmFailure";
        }

        return "redirect:/login";
    }
}
