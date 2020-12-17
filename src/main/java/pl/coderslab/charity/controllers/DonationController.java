package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.dtos.donation.DonationDTO;
import pl.coderslab.charity.dtos.donation.DonationDetailsDTO;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.dtos.donation.PickUpDetailsDTO;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/donation")
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<InstitutionDTO> getAllInstitutions() {
        return institutionService.getAll();
    }

    @GetMapping
    public String addDonation(Model model) {
        model.addAttribute("newDonationDTO", new NewDonationDTO());
        return "form";
    }

    @PostMapping
    public String saveDonation(@Valid NewDonationDTO newDonationDTO, BindingResult result) {
        if (result.hasErrors()){
            return "form";
        }

        donationService.save(newDonationDTO);
        return "form-confirmation";
    }

    @GetMapping("/userDonations")
    public String getUserDonations(Model model){
        model.addAttribute("donations",donationService.getUserDonations());
        return "user/userDonations";
    }

    @GetMapping("/donationDetails/{id}")
    public String getDonationDetails(@PathVariable Long id,Model model){
        DonationDetailsDTO donationDetailsDTO=donationService.getDonationDetails(id);
        model.addAttribute("donation",donationDetailsDTO);
        if (!donationDetailsDTO.getIsPickedUp()){
            PickUpDetailsDTO pickUpDetailsDTO=new PickUpDetailsDTO();
            pickUpDetailsDTO.setDonationId(id);
            model.addAttribute("pickUpDetails",pickUpDetailsDTO);
        }
        return "user/donationDetails";
    }

    @PostMapping("/pickUp")
    public String setPickUp(@ModelAttribute PickUpDetailsDTO pickUpDetailsDTO){
        donationService.setPickUp(pickUpDetailsDTO);
        return "redirect:donationDetails/"+pickUpDetailsDTO.getDonationId().toString();
    }
}
