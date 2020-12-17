package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.coderslab.charity.dtos.password.PasswordDTO;
import pl.coderslab.charity.dtos.user.ChangeEmailDTO;
import pl.coderslab.charity.dtos.user.EditUserDetailsDTO;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.VerificationToken;
import pl.coderslab.charity.entities.base.CurrentUser;
import pl.coderslab.charity.exceptions.InvalidOldPasswordException;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.utilities.LoggedUser;
import pl.coderslab.charity.utilities.email.EmailServiceImpl;
import pl.coderslab.charity.utilities.email.TokenGenerator;
import pl.coderslab.charity.validation.groups.Registration;
import pl.coderslab.charity.validation.groups.Update;

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
    private final SpringDataUserDetailsService springDataUserDetailsService;

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

    public void update(EditUserDetailsDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        userRepository.save(user);
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrentUser currentUser1 = (CurrentUser) springDataUserDetailsService.loadUserByUsername(currentUser.getUsername());

        Authentication newAuth = new UsernamePasswordAuthenticationToken(currentUser1, currentUser.getPassword(), currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }


    public void changePassword(PasswordDTO password) {
        User user = loggedUser.getLoggedUser();
        if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new InvalidOldPasswordException("invalid.old.password.message");
        }
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        userRepository.save(user);
    }

    @Validated(Update.class)
    public void changeEmail(@Valid ChangeEmailDTO emailDTO) {
        User user=loggedUser.getLoggedUser();
        user.setEmail(emailDTO.getNewEmail());
        user.setEnabled(false);
        User savedUser = userRepository.save(user);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(savedUser);
        verificationToken.setToken(tokenGenerator.generateNewToken());
        verificationTokenService.save(verificationToken);

        try {
            emailService.sendSimpleEmail(savedUser.getEmail(), savedUser.getName(), verificationToken.getToken());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        SecurityContextHolder.clearContext();
    }
}
