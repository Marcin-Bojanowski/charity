package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.dtos.donation.NewDonationDTO;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

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
        model.addAttribute("donation", new NewDonationDTO());
        return "form";
    }

    @PostMapping
    public String saveDonation(@ModelAttribute NewDonationDTO newDonationDTO) {

        donationService.save(newDonationDTO);
        return "form-confirmation";
    }
}
