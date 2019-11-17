package pl.coderslab.charity.entity.authentication;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roles")
    private List<User> users;


}
