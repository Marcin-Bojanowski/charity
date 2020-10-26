package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.services.RegistrationService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final RegistrationService registrationService;


    @GetMapping
    public String registrationForm(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "register";
    }

    @PostMapping
    public String register(@Valid UserDTO userDTO, BindingResult result,Model model){
        if (result.hasErrors()){
            return "register";
        }
        try {
            registrationService.register(userDTO);
        } catch (ConstraintViolationException exception){
            model.addAttribute("violations",exception.getConstraintViolations());
            exception.printStackTrace();
            return "register";
        }
        return "login";
    }
}
