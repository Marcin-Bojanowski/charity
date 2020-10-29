package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.coderslab.charity.dtos.user.NewUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.validation.groups.Registration;

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

    @Validated(Registration.class)
    public void registerUser(@Valid NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.getEmail());
        user.setName(newUserDTO.getName());
        user.setSurname(newUserDTO.getSurname());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.getRoles().add("ROLE_USER");
        user.setActive(true);
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Validated(Registration.class)
    public void registerAdmin(@Valid NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.getEmail());
        user.setName(newUserDTO.getName());
        user.setSurname(newUserDTO.getSurname());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.getRoles().add("ROLE_ADMIN");
        user.setActive(true);
        user.setBlocked(false);
        userRepository.save(user);
    }

    public void update(UserDTO userDTO) {
        User user=userRepository.getOne(userDTO.getId());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
    }
}
