package pl.coderslab.charity.dtos.institution;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewInstitutionDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
