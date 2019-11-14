package pl.coderslab.charity.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Getter
    @Setter
    @Min(value = 1)
    private int quantity;

    @Getter
    @Setter
    @ManyToMany
    @NotEmpty
    private List<Category> categories;

    @Getter
    @Setter
    @ManyToOne
    @NotNull
    private Institution institution;

    @Getter
    @Setter
    @NotBlank
    private String street;

    @Getter
    @Setter
    @NotBlank
    private String city;

    @Getter
    @Setter
    @NotBlank
    private String zipCode;

    @Getter
    @Setter
    @NotBlank
    private String phone;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickUpDateTime;

    @Transient
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate pickUpDate;

    @Transient
    @Getter
    @Setter
    @NotNull
    private LocalTime pickUpTime;

    @Getter
    @Setter
    private String pickUpComment;


}
