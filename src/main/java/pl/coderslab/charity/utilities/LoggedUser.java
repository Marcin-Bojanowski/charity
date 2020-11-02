package pl.coderslab.charity.utilities;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.entities.base.CurrentUser;

@Component
public class LoggedUser {
    public Long getLoggedUserId() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUser().getId();
    }

    public User  getLoggedUser(){
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUser();
    }
}