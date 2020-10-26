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


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class NewDonationDTO {
    private Integer quantity;
    private List<Long> categoriesId=new ArrayList<>();
    private Long institutionId;
    private String street;
    private String city;
    private String zipCode;
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
}
