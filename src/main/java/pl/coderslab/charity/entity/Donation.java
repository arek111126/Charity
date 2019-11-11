package pl.coderslab.charity.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    @OneToMany
    private List<Category> categories;

    @Getter @Setter
    @ManyToOne
    private Institution institution;

    @Getter @Setter
    private String street;

    @Getter @Setter
    private String zipCode;

    @Getter @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickUpDateTime;

    @Getter @Setter
    private String pickUpComment;


}
