package pl.coderslab.charity.dtos.institution;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class InstitutionDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
