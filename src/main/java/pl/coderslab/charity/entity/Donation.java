package pl.coderslab.charity.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "donation")
public class Donation {

    @Id
    private int id;
    private int quantity;

    @OneToMany
    private List<Category> categories;

    @OneToOne
    private Institution institution;
    private String street;
    private String zipCode;
    private String pickUpDate;
    private String pickUpTime;
    private String pickUpComment;


}
