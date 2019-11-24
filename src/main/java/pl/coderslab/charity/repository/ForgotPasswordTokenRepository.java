package pl.coderslab.charity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.token.ForgotPasswordToken;

public interface ForgotPasswordTokenRepository extends JpaRepository<ForgotPasswordToken, Integer> {
    ForgotPasswordToken findFirstByToken(String token);

    @Query("Select f from ForgotPasswordToken f where f.user.email = :email")
    ForgotPasswordToken findFirstByUserEmail(@Param("email") String email);

}
