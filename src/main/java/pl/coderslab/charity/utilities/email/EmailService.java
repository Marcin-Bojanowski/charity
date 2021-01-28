package pl.coderslab.charity.utilities.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendActivationEmail (String to,  String name, String token) throws MessagingException;

    void resetPassword (String to,  String name, String token) throws MessagingException;
}
