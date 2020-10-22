package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
private final DonationService donationService;
    @ModelAttribute("institutions")
    public List<InstitutionDTO> getAllInstitutions() {
        return institutionService.getAll();
    }

    @ModelAttribute("quantityOfBags")
    public Integer getQuantityOfBags(){
        return donationService.getQuantityOfBags();
    }

    @ModelAttribute("quantityOfDonations")
    public Integer getQuantityOfDonations(){
        return donationService.getQuantityOfDonations();
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions",institutionService.getAll());
        return "index";
    }
}
