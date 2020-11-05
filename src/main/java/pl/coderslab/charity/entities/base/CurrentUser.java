package pl.coderslab.charity.entities.base;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Data
public class CurrentUser extends User {
    private final pl.coderslab.charity.entities.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
    pl.coderslab.charity.entities.User user) {

        super(username, password, user.getEnabled(), true,true,user.getLocked(), authorities);
        this.user = user;
    }

    public pl.coderslab.charity.entities.User getUser() {
        return user;
    }
}
