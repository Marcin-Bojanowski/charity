package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.dtos.institution.NewInstitutionDTO;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class InstitutionsController {

    private final InstitutionService institutionService;


    @ModelAttribute("institutions")
    public List<InstitutionDTO> getAll(){
        return institutionService.getAll();
    }

    @GetMapping("/institutions")
    public String institutions(@RequestParam Long toEdit, Model model) {
        model.addAttribute("toEditId", toEdit);
        return "institution/institutions";
    }

    @GetMapping("/addInstitution")
    public String addInstitution (Model model){
        model.addAttribute("newInstitution",new NewInstitutionDTO());
        return "institution/institutionForm";
    }

    @PostMapping("/addInstitution")
    public String saveInstitution(@Valid NewInstitutionDTO newInstitution, BindingResult result){
        if (result.hasErrors()){
            return "institution/institutionForm";
        }
        institutionService.save(newInstitution);
       return "redirect:/admin/institutions";
    }

    @GetMapping("/editInstitution/{id}")
    public String editInstitution(@PathVariable Long id, Model model){
        model.addAttribute("institution",institutionService.getById(id));
        return "institution/editInstitutionForm";

    }

    @PostMapping("/editInstitution")
    public String updateInstitution(@Valid InstitutionDTO institutionDTO){
        institutionService.update(institutionDTO);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/deleteInstitution/{id}")
    public String deleteInstitution(@PathVariable Long id){
        institutionService.delete(id);
        return "redirect:/admin/institutions";
    }
}
