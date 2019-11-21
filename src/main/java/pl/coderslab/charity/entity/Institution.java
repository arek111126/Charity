package pl.coderslab.charity.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;


    @Getter @Setter
    @ManyToMany
    private List<Category> categories;

    @Transient
    @Getter @Setter
    private int hiddenId;
}
