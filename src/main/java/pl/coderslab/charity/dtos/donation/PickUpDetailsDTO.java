package pl.coderslab.charity.dtos.donation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class PickUpDetailsDTO {
    private Long donationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate donePickUpDate;
    private LocalTime donePickUpTime;
}
