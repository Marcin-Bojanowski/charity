package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {

    List<Donation> getAllByUserId(Long id);

    List<Donation> getAllByInstitution(Institution institution);

}
