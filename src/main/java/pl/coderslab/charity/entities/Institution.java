package pl.coderslab.charity.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.entities.superClasses.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "institution")
public class Institution  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
