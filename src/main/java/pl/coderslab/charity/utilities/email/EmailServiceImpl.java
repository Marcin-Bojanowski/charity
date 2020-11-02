package pl.coderslab.charity.utilities.email;

import lombok.RequiredArgsConstructor;
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

    @Override
    public void sendSimpleEmail(String to, String name, String token) throws MessagingException {

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("token", token);
        String body = templateEngine.process("template", context);
        templateEngine.process("template", context);


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"UTF-8");
        messageHelper.setFrom("java.sending.emails@gmail.com");
        messageHelper.setTo(to);
        messageHelper.setSubject("Aktywacja konta w serwisie charity");
        messageHelper.setText(body,true);
        mailSender.send(message);



    }
}
