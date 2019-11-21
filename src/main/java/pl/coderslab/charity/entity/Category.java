package pl.coderslab.charity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private String name;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy = "categories")
    private List<Institution> institutions;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", institutions=" + institutions +
                '}';
    }
}
