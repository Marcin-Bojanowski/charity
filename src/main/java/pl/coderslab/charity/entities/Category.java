package pl.coderslab.charity.entities;

import lombok.Data;


import javax.persistence.*;
@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
