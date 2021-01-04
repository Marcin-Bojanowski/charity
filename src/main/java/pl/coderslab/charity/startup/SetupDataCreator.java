package pl.coderslab.charity.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.UserService;

@Component
@RequiredArgsConstructor
public class SetupDataCreator implements ApplicationRunner {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createMainUsers();
    }

    private void createMainUsers() {
        createUserIfNotExists("admin@adminmail.com", "admin", "admin", "admin",true,true,"ROLE_ADMIN");
        createUserIfNotExists("root@rootmail.com", "root", "root", "root",true,true,"ROLE_USER");
    }

    public void createUserIfNotExists(String email, String name, String surname, String password, Boolean accountEnabled,
                                      Boolean accountLocked, String role) {
        if (userService.getByEmail(email) == null) {
            User user = new User();
            user.setSurname(surname);
            user.setName(name);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(accountEnabled);
            user.setLocked(accountLocked);
            user.getRoles().add(role);
            userService.save(user);
        }
    }
}
