package pl.coderslab.charity.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.authentication.User;

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

    @Getter @Setter
    @Min(value = 1)
    private int quantity;

    @Getter @Setter
    @ManyToMany
    @NotEmpty
    private List<Category> categories;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private Institution institution;

    @Getter @Setter
    @NotBlank
    private String street;

    @Getter
    @Setter
    @NotBlank
    private String city;

    @Getter @Setter
    @NotBlank
    private String zipCode;

    @Getter @Setter
    @NotBlank
    private String phone;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickUpDateTime;

    @Transient
    @Getter @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate pickUpDate;

    @Transient
    @Getter @Setter
    @NotNull
    private LocalTime pickUpTime;

    @Getter @Setter
    private String pickUpComment;

    @Getter @Setter
    private int received;

    @Getter @Setter
    private String creationDate;

    @Getter @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime realPickUpDateTime;

    @Getter @Setter
    @ManyToOne
    @JsonIgnore
    private User user;

    @PrePersist
    public void setCreatedDate() {
        LocalDate localDate = LocalDate.now();
        this.creationDate = localDate.toString();
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", categories=" + categories +
                ", institution=" + institution +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", pickUpDateTime=" + pickUpDateTime +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                ", received=" + received +
                ", creationDate='" + creationDate + '\'' +
                ", realPickUpDateTime=" + realPickUpDateTime +
                ", user=" + user +
                '}';
    }
}
