package pl.coderslab.charity.controllers;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.email.EmailDTO;
import pl.coderslab.charity.dtos.password.NewPasswordDTO;
import pl.coderslab.charity.entities.VerificationToken;
import pl.coderslab.charity.services.ResetPasswordService;
import pl.coderslab.charity.services.VerificationTokenService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping("/resetPassword")
@Slf4j
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;
    private final VerificationTokenService verificationTokenService;
    private final String error="reset.password.error";

    @GetMapping
    public String resetPassword(Model model) {
        model.addAttribute("email", new EmailDTO());
        return "user/resetPassword";
    }

    @PostMapping("/email")
    public String resetPasswordEmail(@Valid @ModelAttribute("email") EmailDTO email, BindingResult result) {
        if (result.hasErrors()) {
            log.info(result.toString());
            return "user/resetPassword";
        }
        try {
            resetPasswordService.sendEmail(email);
        } catch (ConstraintViolationException exception) {
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                String message = violation.getMessage();
                Path propertyPath = violation.getPropertyPath();
                log.info(message);
                String property = Iterables.getLast(propertyPath).toString();
                result.rejectValue(property, message);

            }
            return "user/resetPassword";
        }
        return "redirect:/";
    }

    @GetMapping("/getToken/{token}")
    public String checkToken(@PathVariable String token, Model model) {
        VerificationToken verificationToken = verificationTokenService.getToken(token);
        if (verificationToken == null) {
            model.addAttribute("error",error);
            return "errors/error";
        }
        NewPasswordDTO newPasswordDTO=new NewPasswordDTO();
        newPasswordDTO.setToken(verificationToken.getToken());
        model.addAttribute("newPassword",newPasswordDTO);
        return "user/newPassword";
    }

    @PostMapping("/save")
    public String savePassword(@Valid @ModelAttribute("newPassword") NewPasswordDTO newPasswordDTO, BindingResult result, Model model){
        VerificationToken verificationToken = verificationTokenService.getToken(newPasswordDTO.getToken());
        if (verificationToken == null) {
            model.addAttribute("error",error);
            return "errors/error";
        }
        if (result.hasErrors()){
            return "user/newPassword";
        }
        resetPasswordService.saveNewPassword(newPasswordDTO);
        return "redirect:/login";
    }
}
