package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.donation.DonationDTO;
import pl.coderslab.charity.dtos.donation.DonationDetailsDTO;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.dtos.donation.PickUpDetailsDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.utilities.CustomMapper;
import pl.coderslab.charity.utilities.LoggedUser;

import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private CustomMapper customMapper;
private LoggedUser loggedUser;

    @Autowired
    private void setLoggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Autowired
    private void setCustomMapper(CustomMapper customMapper) {
        this.customMapper = customMapper;
    }


    public Integer getQuantityOfBags() {
        List<Donation> donations = donationRepository.findAll();
        return donations.stream().mapToInt(Donation::getQuantity).sum();
    }

    public Integer getQuantityOfDonations() {
        return (int) donationRepository.count();
    }

    public void save(NewDonationDTO newDonationDTO) {
        Donation donation=customMapper.map(newDonationDTO);
        donation.setIsPickedUp(false);
        donationRepository.save(donation);
    }


    public List<DonationDTO> getUserDonations() {
        List<Donation> donations = donationRepository.getAllByUserId(loggedUser.getLoggedUserId());
        return donations.stream().map(donation -> customMapper.mapToDonationDTO(donation)).collect(Collectors.toList());
    }

    public DonationDetailsDTO getDonationDetails(Long id) {
        return customMapper.mapToDonationDetails(donationRepository.getOne(id));
    }

    public void setPickUp(PickUpDetailsDTO pickUpDetailsDTO) {
        Donation donation=donationRepository.getOne(pickUpDetailsDTO.getDonationId());
        donation.setIsPickedUp(true);
        donation.setDonePickUpDate(pickUpDetailsDTO.getDonePickUpDate());
        donation.setDonePickUpTime(pickUpDetailsDTO.getDonePickUpTime());
        donationRepository.save(donation);
    }

    public List<Donation> getAllByInstitution(Institution institution) {
        return donationRepository.getAllByInstitution(institution);
    }

    public void update(Donation donation) {
        donationRepository.save(donation);
    }
}
