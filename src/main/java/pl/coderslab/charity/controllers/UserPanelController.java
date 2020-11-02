package pl.coderslab.charity.controllers;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.password.PasswordDTO;
import pl.coderslab.charity.dtos.user.EditUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.exceptions.InvalidOldPasswordException;
import pl.coderslab.charity.services.RegistrationService;
import pl.coderslab.charity.services.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/userPanel")
public class UserPanelController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping
    public String getUserPanel() {
        return "userPanel";
    }

    @GetMapping("/editUser")
    public String getUserToEdit(Model model) {
        model.addAttribute("user", userService.getUserToEdit());
        return "user/editUser";
    }

    @PostMapping("/editUser")
    public String updateAdmin(@ModelAttribute EditUserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "user/editUser";
        }
        try {
            registrationService.update(userDTO);
        } catch (ConstraintViolationException exception) {
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                Path propertyPath = violation.getPropertyPath();
                String property = Iterables.getLast(propertyPath).toString();
                result.rejectValue(property, message);
            }
            return "user/editUser";
        }
        return "redirect:/userPanel";
    }

    @GetMapping("/changePassword")
    public String getPasswordDTO(Model model) {
        model.addAttribute("password", new PasswordDTO());
        return "user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePasswordDTO(@Valid PasswordDTO password, BindingResult result) {
        if (result.hasErrors()) {
            return "user/changePassword";
        }
        try {
            registrationService.changePassword(password);
        } catch (InvalidOldPasswordException exception){
            exception.printStackTrace();
            return "user/changePassword";
        }

        return "redirect:/userPanel";
    }
}
