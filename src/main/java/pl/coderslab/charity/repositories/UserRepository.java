package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    boolean existsByEmail(String email);


    List<User> findAllByRoles(String role);
}
