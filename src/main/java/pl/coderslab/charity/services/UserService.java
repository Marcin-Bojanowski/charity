package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.user.EditUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.utilities.CustomMapper;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomMapper customMapper;

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<UserDTO> getAllAdminsDTO() {
        List<User> admins = userRepository.findAllByRoles("ROLE_ADMIN");
        return admins.stream().map(customMapper::map).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersDTO() {
        List<User> admins = userRepository.findAllByRoles("ROLE_USER");
        return admins.stream().map(customMapper::map).collect(Collectors.toList());
    }

    public UserDTO getUserToEdit(Long id) {
        return customMapper.map(userRepository.getOne(id));
    }

    public void delete(Long id) {
        User user=userRepository.getOne(id);
        userRepository.delete(user);
    }
}
