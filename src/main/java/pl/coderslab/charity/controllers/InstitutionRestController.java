package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institutions") @Validated
public class InstitutionRestController {
    private final InstitutionService institutionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Institution> getAllInstitutions() {
        return institutionService.getAll();
    }

    @PostMapping
    // 201 Created - poprawny zapis wartości i taki status zwracamy
    //               + Location: adres utworzonego zasobu, np: /api/institutions/14
    // 400 Bad Request
    public ResponseEntity saveInstitution(@Valid @RequestBody Institution institution) {
        institutionService.addInstitution(institution);
        return ResponseEntity.created(URI.create("/api/institutions/" + institution.getId())).build();
//        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/institutions/"+ institution.getId())).build();
    }

    @GetMapping("/{id}")
    // 200 OK - jak znaleziono
    // 404 Not found - jak nie znaleziono
    public ResponseEntity getOne(@PathVariable long id) {
        Institution institution = institutionService.findInstitutionById(id);
        if (institution != null) {
            return ResponseEntity.status(HttpStatus.OK).body(institution);
//            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveEditedInstitution(@PathVariable long id, @Valid @RequestBody Institution institution) {
        if (institution.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Błędne id");
        }
        institutionService.updateInstitution(institution);
    }

    @DeleteMapping("/{id}")
    // 200 OK - jak usunięto, bez treści
    // 404 Not found - czegoś czego nie ma
    public ResponseEntity deleteInstitutionById(@PathVariable long id){
        Institution institution = institutionService.findInstitutionById(id);
        if (institution != null) {
            institutionService.deleteInstitutionById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

