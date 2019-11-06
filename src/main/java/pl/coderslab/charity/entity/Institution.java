package pl.coderslab.charity.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "institution")
public class Institution {
    @Id
    private int id;

    private String name;
    private String description;
}
