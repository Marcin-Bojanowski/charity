package pl.coderslab.charity.entities;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "institution")
public class Institution  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
