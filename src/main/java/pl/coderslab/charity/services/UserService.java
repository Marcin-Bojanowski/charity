package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.user.EditUserDetailsDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.base.CurrentUser;
import pl.coderslab.charity.exceptions.LoggedAdminDeleteException;
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
    private CustomMapper customMapper;
    private LoggedUser loggedUser;

    @Autowired
    private void setCustomMapper(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }

    @Autowired
    private void setLoggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }


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

    public EditUserDetailsDTO getUserToEdit() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(currentUser.toString());
        return customMapper.mapLoggedUser(loggedUser.getLoggedUser());
    }

    public void delete(Long id) {
        User user = userRepository.getOne(id);
        User currentAdmin = loggedUser.getLoggedUser();
        if (user.equals(currentAdmin)) throw new LoggedAdminDeleteException("delete.logged.admin.message");
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }
}
