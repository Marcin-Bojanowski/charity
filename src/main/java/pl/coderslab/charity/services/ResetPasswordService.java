package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.coderslab.charity.dtos.email.EmailDTO;
import pl.coderslab.charity.dtos.password.NewPasswordDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.VerificationToken;
import pl.coderslab.charity.utilities.email.EmailService;
import pl.coderslab.charity.utilities.email.TokenGenerator;
import pl.coderslab.charity.validation.groups.Update;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class ResetPasswordService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Validated(Update.class)
    public void sendEmail(@Valid EmailDTO email) {
        User user = userService.getByEmail(email.getEmail());
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(tokenGenerator.generateNewToken());
        verificationTokenService.save(verificationToken);

        try {
            emailService.resetPassword(user.getEmail(), user.getName(), verificationToken.getToken());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void saveNewPassword(NewPasswordDTO newPasswordDTO) {
        VerificationToken verificationToken = verificationTokenService.getToken(newPasswordDTO.getToken());
        User user = userService.getUserById(verificationToken.getUser().getId());
        user.setPassword(passwordEncoder.encode(newPasswordDTO.getPassword()));
        userService.save(user);
        verificationTokenService.deleteToken(verificationToken);
    }
}
