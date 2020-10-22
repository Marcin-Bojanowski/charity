package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Category;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select c from Category c")
    List<Category> getAll();

    Category getById(Long id);
}
