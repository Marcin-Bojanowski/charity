package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;

import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DonationService {
    private final DonationRepository donationRepository;
//    private final ModelMapper modelMapper;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    public Integer getQuantityOfBags() {
        List<Donation> donations = donationRepository.getAll();
        return donations.stream().mapToInt(Donation::getQuantity).sum();
    }

    public Integer getQuantityOfDonations() {
        return donationRepository.getQuantityOfDonations();
    }

    public void save(NewDonationDTO newDonationDTO) {
        donationRepository.save(newDonationMapper(newDonationDTO));
    }

    private Donation newDonationMapper(NewDonationDTO newDonationDTO) {
        ModelMapper modelMapper=new ModelMapper();
        PropertyMap<NewDonationDTO, Donation> propertyMap = new PropertyMap<NewDonationDTO, Donation>() {
            @Override
            protected void configure() {

                skip(destination.getId());
                skip(destination.getCategories());
                skip(destination.getInstitution());
//                map(institutionService.getById(source.getInstitutionId()),destination);
//                skip(destination.getInstitution());
            }
        };
        modelMapper.addMappings(propertyMap);
        Donation donation = modelMapper.map(newDonationDTO, Donation.class);
        System.out.println(donation);
        for (Long id : newDonationDTO.getCategoriesId()) {
            donation.getCategories().add(categoryService.getById(id));
        }
        donation.setInstitution(institutionService.getById(newDonationDTO.getInstitutionId()));
        System.out.println(donation);
        return donation;
    }
}
