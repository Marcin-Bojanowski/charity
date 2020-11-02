package pl.coderslab.charity.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        setDefaultFailureUrl("/login.html?error=true");
//        super.onAuthenticationFailure(request, response, exception);
//
//        Locale locale = localeResolver.resolveLocale(request);
//
//        String errorMessage = messages.getMessage("message.badCredentials", null, locale);
//
//        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
//            errorMessage = messages.getMessage("auth.message.disabled", null, locale);
//        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
//            errorMessage = messages.getMessage("auth.message.expired", null, locale);
//        }
//
//        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
//    }
    }
}
