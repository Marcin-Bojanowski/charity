package pl.coderslab.charity.controllers;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
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
public class AdminsController {

    private final UserService userService;
private final RegistrationService registrationService;
    @ModelAttribute("admins")
    public List<UserDTO> getAdmins() {
      return   userService.getAllAdminsDTO();
    }

    @GetMapping("/admins")
    public String getAllAdmins(){
        return "admin/admins";
    }

    @GetMapping("/addAdmin")
    public String addAdmin(Model model){
        model.addAttribute("admin",new NewUserDTO());
        return "admin/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String saveAdmin(@Valid NewUserDTO newUserDTO, BindingResult result){
        if (result.hasErrors()){
            return "admin/addAdmin";
        }
        try {
            registrationService.registerAdmin(newUserDTO);
        } catch (ConstraintViolationException exception){
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                Path propertyPath =violation.getPropertyPath();
                String property = Iterables.getLast(propertyPath).toString();
                result.rejectValue(property,message);
            }
            return "admin/addAdmin";
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("/editAdmin/{id}")
    public String editAdmin(@PathVariable Long id, Model model){
        model.addAttribute("admin",userService.getUserToEdit(id));
        return "admin/editAdmin";
    }

    @PostMapping("/editAdmin")
    public String updateAdmin(@ModelAttribute UserDTO userDTO, BindingResult result){
        if (result.hasErrors()){
            return "admin/editAdmin";
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
            return "admin/editAdmin";
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin/admins";
    }
}
