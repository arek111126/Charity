package pl.coderslab.charity.entity.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.validate.ConfirmPassword;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "user")
@ConfirmPassword
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;



    @Getter
    @Setter
    @Transient
    private String retypePassword;



    @Getter
    @Setter
    private int enabled;


    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private List<Role> roles;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id_user")
    private List<Donation> donations;
    public User() {
        roles = new ArrayList<>();
    }

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String secondName;






}
