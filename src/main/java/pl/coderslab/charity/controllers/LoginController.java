package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller

@Slf4j
public class LoginController {

    private final String LOCKED_MESSAGE="user.locked.message";

    @GetMapping("/login")
    public String login(){

        return "login";

    }

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model, Error error){
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {

                if (ex instanceof LockedException){
                    errorMessage=LOCKED_MESSAGE;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
