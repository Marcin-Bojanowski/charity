package pl.coderslab.charity.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entities.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "donation")

public class Donation extends BaseEntity {

    private Integer quantity;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ManyToOne
    private Institution institution;
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

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }


}
