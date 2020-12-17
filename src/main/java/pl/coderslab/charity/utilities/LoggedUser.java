package pl.coderslab.charity.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.base.CurrentUser;
import pl.coderslab.charity.services.UserService;

@Component

public class LoggedUser {

    private UserService userService;

    @Autowired
    private void setLoggedUser(UserService userService) {
        this.userService = userService;
    }

    public Long getLoggedUserId() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUser().getId();
    }

    public User getLoggedUser() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserById(currentUser.getUser().getId());
    }

}