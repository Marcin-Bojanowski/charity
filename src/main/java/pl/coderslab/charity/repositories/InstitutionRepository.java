package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Institution;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("select i from Institution i")
    List<Institution> getAll();

    Institution getById(Long id);
}
