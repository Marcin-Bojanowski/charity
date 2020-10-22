package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.Donation;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
   @Query("select d from Donation d")
    List<Donation> getAll();

   @Query("select count (d.id) from Donation d")
    Integer getQuantityOfDonations();
}
