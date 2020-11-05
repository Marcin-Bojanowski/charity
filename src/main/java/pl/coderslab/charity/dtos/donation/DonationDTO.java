package pl.coderslab.charity.dtos.donation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.dtos.category.CategoryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationDTO {
private Long id;
    private Integer quantity;
    private List<String> categoriesNames;
    private String institutionName;

    private LocalDateTime createDate;
    private Boolean isPickedUp;
    private LocalDate donePickUpDate;
    private LocalTime donePickUpTime;
}
