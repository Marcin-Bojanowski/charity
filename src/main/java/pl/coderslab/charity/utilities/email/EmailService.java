package pl.coderslab.charity.utilities.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleEmail (String to,  String name, String token) throws MessagingException;
}
