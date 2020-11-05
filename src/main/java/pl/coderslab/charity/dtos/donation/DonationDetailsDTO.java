package pl.coderslab.charity.dtos.donation;

import lombok.Data;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Institution;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDetailsDTO {
    private Integer quantity;
    private List<String> categoriesNames;
    private String institutionName;
    private String street;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    private Boolean isPickedUp;
    private LocalDate donePickUpDate;
    private LocalTime donePickUpTime;
    private LocalDateTime createDate;
}
