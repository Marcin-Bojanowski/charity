package pl.coderslab.charity.entities.base;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.utilities.LoggedUser;

import javax.persistence.*;

@MappedSuperclass
@Data


public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @PrePersist
    public void setUserId() {
        LoggedUser loggedUser=new LoggedUser();
        this.userId = loggedUser.getLoggedUserId();
    }
}
