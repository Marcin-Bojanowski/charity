package pl.coderslab.charity.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "donation")
@ToString
public class Donation   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToMany
    private List<Category> categories=new ArrayList<>();
    @ManyToOne
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String phoneNumber;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;


}
