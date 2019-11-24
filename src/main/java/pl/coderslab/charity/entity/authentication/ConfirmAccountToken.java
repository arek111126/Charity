package pl.coderslab.charity.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirm_account_token")
public class ConfirmAccountToken {

  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String token;
  
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    @Getter @Setter
    private User user;


    @Override
    public String toString() {
        return "ConfirmAccountToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }

    public ConfirmAccountToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
}