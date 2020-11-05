package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.user.EditUserDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.base.CurrentUser;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.utilities.CustomMapper;
import pl.coderslab.charity.utilities.LoggedUser;


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
private final LoggedUser loggedUser;
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

    public EditUserDTO getUserToEdit() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(currentUser.toString());
        return customMapper.mapLoggedUser(loggedUser.getLoggedUser());
    }

    public void delete(Long id) {
        User user=userRepository.getOne(id);
        userRepository.delete(user);
    }

    public User getUserById(Long id){
        return userRepository.getOne(id);
    }
}
