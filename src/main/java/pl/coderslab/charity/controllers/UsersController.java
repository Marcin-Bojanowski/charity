package pl.coderslab.charity.controllers;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.services.RegistrationService;
import pl.coderslab.charity.services.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UsersController {
    private final UserService userService;
    private final RegistrationService registrationService;

    @ModelAttribute("users")
    public List<UserDTO> getUsers() {
        return   userService.getAllUsersDTO();
    }

    @GetMapping("/users")
    public String getAllUsers(){
        return "user/users";
    }



    @GetMapping("/editUser/{id}")
    public String editAdmin(@PathVariable Long id, Model model){
        model.addAttribute("user",userService.getUserToEdit(id));
        return "user/editUser";
    }

    @PostMapping("/editUser")
    public String updateAdmin(@ModelAttribute UserDTO userDTO, BindingResult result){
        if (result.hasErrors()){
            return "user/editUser";
        }
        try {
            registrationService.update(userDTO);
        } catch (ConstraintViolationException exception){
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                Path propertyPath =violation.getPropertyPath();
                String property = Iterables.getLast(propertyPath).toString();
                result.rejectValue(property,message);
            }
            return "user/editUser";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
