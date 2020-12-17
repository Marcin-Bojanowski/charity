package pl.coderslab.charity.dtos.donation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.dtos.category.CategoryDTO;
import pl.coderslab.charity.dtos.institution.InstitutionDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Institution;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class NewDonationDTO {
    @NotNull
    private Integer quantity;
    @NotEmpty
    private List<Long> categoriesId=new ArrayList<>();
    @NotNull
    private Long institutionId;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String phoneNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @NotNull
    private LocalTime pickUpTime;

    private String pickUpComment;
}
