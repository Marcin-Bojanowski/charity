package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
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

    private final String LOCKED_MESSAGE="auth.message.locked";
    private final String DISABLED_MESSAGE="auth.message.disabled";
    private final String BAD_CREDENTIALS_MESSAGE="auth.message.bad.credentials";

    @GetMapping("/login")
    public String login(){

        return "login";

    }

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model, Error error){
        HttpSession session = request.getSession(false);
        String errorMessage = BAD_CREDENTIALS_MESSAGE;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {

                if (ex instanceof LockedException){
                    errorMessage=LOCKED_MESSAGE;
                } else if (ex instanceof DisabledException){
                    errorMessage=DISABLED_MESSAGE;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
