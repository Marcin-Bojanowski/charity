package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.utilities.CustomMapper;

import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private CustomMapper customMapper;
//
//    @Autowired
//    private DonationService(DonationRepository donationRepository){
//        this.donationRepository=donationRepository;
//    }

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
        log.info("{}", newDonationDTO);
        donationRepository.save(customMapper.map(newDonationDTO));
    }


}
