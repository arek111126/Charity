package pl.coderslab.charity.entity.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.charity.entity.authentication.User;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forgot_password_token")
public class ForgotPasswordToken {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    @Getter
    @Setter
    private User user;


    @Override
    public String toString() {
        return "ConfirmAccountToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }

    public ForgotPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
}