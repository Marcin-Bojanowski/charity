package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.charity.dtos.password.PasswordDTO;
import pl.coderslab.charity.dtos.user.EditUserDTO;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.VerificationToken;
import pl.coderslab.charity.exceptions.InvalidOldPasswordException;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.utilities.LoggedUser;
import pl.coderslab.charity.utilities.email.EmailServiceImpl;
import pl.coderslab.charity.utilities.email.TokenGenerator;
import pl.coderslab.charity.validation.groups.Registration;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final VerificationTokenService verificationTokenService;
    private final TokenGenerator tokenGenerator;
    private final LoggedUser loggedUser;

    @Validated(Registration.class)
    public void registerUser(@Valid NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.getEmail());
        user.setName(newUserDTO.getName());
        user.setSurname(newUserDTO.getSurname());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setEnabled(false);
        user.setLocked(true);
        user.getRoles().add("ROLE_USER");
        User savedUser = userRepository.save(user);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(savedUser);
        verificationToken.setToken(tokenGenerator.generateNewToken());
        verificationTokenService.save(verificationToken);

        try {
            emailService.sendSimpleEmail(newUserDTO.getEmail(), savedUser.getName(), verificationToken.getToken());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Validated(Registration.class)
    public void registerAdmin(@Valid NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.getEmail());
        user.setName(newUserDTO.getName());
        user.setSurname(newUserDTO.getSurname());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setEnabled(true);
        user.setLocked(true);
        user.getRoles().add("ROLE_ADMIN");
        userRepository.save(user);
    }

    public void update(UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setLocked(userDTO.getLocked());
        userRepository.save(user);
    }

    public void update(EditUserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
    }

    public void changePassword(PasswordDTO password) {
        User user = loggedUser.getLoggedUser();
        if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new InvalidOldPasswordException("invalid.old.password.message");
        }
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        userRepository.save(user);
    }
}
