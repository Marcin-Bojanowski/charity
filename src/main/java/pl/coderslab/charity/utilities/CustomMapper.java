package pl.coderslab.charity.utilities;


import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import lombok.Getter;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.dtos.donation.DonationDTO;
import pl.coderslab.charity.dtos.donation.DonationDetailsDTO;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;

import pl.coderslab.charity.dtos.institution.NewInstitutionDTO;
import pl.coderslab.charity.dtos.user.EditUserDetailsDTO;
import pl.coderslab.charity.dtos.user.UserDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;

import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.InstitutionService;

import java.util.stream.Collectors;


@Getter
@Setter
@Component
@Slf4j
public class CustomMapper {
    private InstitutionService institutionService;
    private CategoryService categoryService;


    @Autowired
    private void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    private void setInstitutionService(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }


    public UserDTO map(User user) {
        Mapper<User, UserDTO> mapper = Mapping
                .from(User.class)
                .to(UserDTO.class)
                .omitInSource(User::getRoles)
                .omitInSource(User::getPassword)
                .omitInSource(User::getEnabled)
                .mapper();

        return mapper.map(user);
    }

    public EditUserDetailsDTO mapLoggedUser(User user) {
        Mapper<User, EditUserDetailsDTO> mapper = Mapping
                .from(User.class)
                .to(EditUserDetailsDTO.class)
                .omitInSource(User::getEmail)
                .omitInSource(User::getRoles)
                .omitInSource(User::getPassword)
                .omitInSource(User::getEnabled)
                .omitInSource(User::getLocked)
                .mapper();

        return mapper.map(user);
    }

    public InstitutionDTO map(Institution institution) {
        Mapper<Institution, InstitutionDTO> mapper = Mapping
                .from(Institution.class)
                .to(InstitutionDTO.class)
                .mapper();
        return mapper.map(institution);
    }

    public CategoryDTO map(Category category) {
        Mapper<Category, CategoryDTO> mapper = Mapping
                .from(Category.class)
                .to(CategoryDTO.class)
                .mapper();

        return mapper.map(category);
    }

    public Institution map(NewInstitutionDTO newInstitutionDTO) {
        Mapper<NewInstitutionDTO, Institution> mapper = Mapping
                .from(NewInstitutionDTO.class)
                .to(Institution.class)
                .omitInDestination(Institution::getId)
                .mapper();

        return mapper.map(newInstitutionDTO);
    }



    public DonationDTO mapToDonationDTO(Donation donation) {
        Mapper<Donation, DonationDTO> mapper = Mapping
                .from(Donation.class)
                .to(DonationDTO.class)
                .omitInSource(Donation::getCity)
                .omitInSource(Donation::getPhoneNumber)
                .omitInSource(Donation::getStreet)
                .omitInSource(Donation::getZipCode)
                .omitInSource(Donation::getPickUpComment)
                .omitInSource(Donation::getInstitution)
                .omitInSource(Donation::getCategories)
                .omitInSource(Donation::getUserId)
                .omitInSource(Donation::getPickUpDate)
                .omitInSource(Donation::getPickUpTime)
                .set(DonationDTO::getCategoriesNames)
                .with(donation.getCategories().stream().map(Category::getName).collect(Collectors.toList()))
               .set(DonationDTO::getInstitutionName)
                .with(donation.getInstitution().getName())
                .mapper();
        return mapper.map(donation);
    }

    public DonationDetailsDTO mapToDonationDetails(Donation donation){
        Mapper<Donation, DonationDetailsDTO> mapper = Mapping
                .from(Donation.class)
                .to(DonationDetailsDTO.class)
                .omitInSource(Donation::getId)
                .omitInSource(Donation::getUserId)
                .omitInSource(Donation::getCategories)
                .omitInSource(Donation::getInstitution)
                .set(DonationDetailsDTO::getCategoriesNames)
                .with(donation.getCategories().stream().map(Category::getName).collect(Collectors.toList()))
                .set(DonationDetailsDTO::getInstitutionName)
                .with(donation.getInstitution().getName())
                .mapper();
        return mapper.map(donation);
    }

    public Donation map(NewDonationDTO newDonationDTO) {
        Mapper<NewDonationDTO, Donation> mapper = Mapping
                .from(NewDonationDTO.class)
                .to(Donation.class)
                .omitInDestination(Donation::getId)
                .omitInDestination(Donation::getUserId)
                .omitInDestination(Donation::getIsPickedUp)
                .omitInDestination(Donation::getDonePickUpDate)
                .omitInDestination(Donation::getDonePickUpTime)
                .omitInSource(NewDonationDTO::getInstitutionId)
                .omitInSource(NewDonationDTO::getCategoriesId)
                .omitInDestination(Donation::getCreateDate)
                .set(Donation::getInstitution)
                .with(institutionService.getById(newDonationDTO.getInstitutionId()))
                .set(Donation::getCategories)
                .with(newDonationDTO.getCategoriesId().stream().map(categoryService::getById).collect(Collectors.toList()))
                .mapper();

        Donation donation = mapper.map(newDonationDTO);
        log.info("{}", donation);
        return donation;
    }

}
