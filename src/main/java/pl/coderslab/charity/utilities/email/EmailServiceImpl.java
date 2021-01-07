package pl.coderslab.charity.utilities.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendSimpleEmail(String to, String name, String token) throws MessagingException {

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("token", token);
        String body = templateEngine.process("template", context);
        templateEngine.process("template", context);


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"UTF-8");
        messageHelper.setFrom(username);
        messageHelper.setTo(to);
        messageHelper.setSubject("Aktywacja konta w serwisie charity");
        messageHelper.setText(body,true);
        mailSender.send(message);



    }
}
