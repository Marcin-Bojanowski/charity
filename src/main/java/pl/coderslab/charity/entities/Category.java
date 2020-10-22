package pl.coderslab.charity.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.entities.superClasses.BaseEntity;

import javax.persistence.*;
@ToString
@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
