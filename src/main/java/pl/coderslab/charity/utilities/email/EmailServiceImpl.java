package pl.coderslab.charity.utilities.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${reset.password.url}")
    private String resetPasswordUrl;

    @Value("${activate.account.url}")
    private String activateAccountUrl;


    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendActivationEmail(String to, String name, String token) throws MessagingException {

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("token", token);
        StringBuilder url = new StringBuilder(activateAccountUrl);
        url.append(token);
        context.setVariable("url", url);
        String body = templateEngine.process("emailTemplates/accountActivation", context);
        templateEngine.process("emailTemplates/accountActivation", context);


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(username);
        messageHelper.setTo(to);
        messageHelper.setSubject("Aktywacja konta w serwisie charity");
        messageHelper.setText(body, true);
        mailSender.send(message);

    }

    @Override
    public void resetPassword(String to, String name, String token) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        StringBuilder url = new StringBuilder(resetPasswordUrl);
        url.append(token);
        context.setVariable("url", url);
        String body = templateEngine.process("emailTemplates/resetPassword", context);
        templateEngine.process("emailTemplates/resetPassword", context);


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(username);
        messageHelper.setTo(to);
        messageHelper.setSubject("Resetowanie hasła w serwisie Charity");
        messageHelper.setText(body, true);
        mailSender.send(message);
    }


}
