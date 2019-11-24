package pl.coderslab.charity.entity.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.validate.ConfirmPassword;
import pl.coderslab.charity.validateGroup.EmailValidationGroup;
import pl.coderslab.charity.validateGroup.ValidationUserInfoGroup;
import pl.coderslab.charity.validateGroup.ValidationUserPasswordGroup;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Table(name = "user")
@ConfirmPassword(groups = ValidationUserPasswordGroup.class)
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @NotBlank(groups = {ValidationUserInfoGroup.class, EmailValidationGroup.class})
    @Email(groups = ValidationUserInfoGroup.class)
    private String email;

    @Getter
    @Setter
    @NotBlank(groups = ValidationUserPasswordGroup.class)
    @Size(min = 8, groups = ValidationUserPasswordGroup.class)
    @Pattern(regexp = ".*[a-z]+.*", groups = ValidationUserPasswordGroup.class, message = "{pl.coderslab.charity.entity.authentication.User.upper.case}")
    @Pattern(regexp = ".*[A-Z]+.*", groups = ValidationUserPasswordGroup.class, message = "{pl.coderslab.charity.entity.authentication.User.lower.case}")
    @Pattern(regexp = ".*\\d+.*", groups = ValidationUserPasswordGroup.class, message = "{pl.coderslab.charity.entity.authentication.User.digits}")
    @Pattern(regexp = ".*\\W+.*", groups = ValidationUserPasswordGroup.class, message = "{pl.coderslab.charity.entity.authentication.User.special.sign}")

    private String password;


    @Getter
    @Setter
    @Transient
    @NotBlank(groups = ValidationUserPasswordGroup.class)
    @Size(min = 8,max = 30,groups = ValidationUserPasswordGroup.class)
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
    @OneToMany(mappedBy = "user")
    private List<Donation> donations;

    public User() {
        roles = new ArrayList<>();
    }

    @Getter
    @Setter
    @NotBlank(groups = ValidationUserInfoGroup.class)
    private String firstName;

    @Getter
    @Setter
    @NotBlank(groups = ValidationUserInfoGroup.class)
    private String secondName;

    @Transient
    @Getter
    @Setter
    private boolean changePassword;

    @Transient
    @Getter
    @Setter
    private int hiddenId;


}
