package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.token.ConfirmAccountToken;

public interface ConfirmAccountTokenRepository extends JpaRepository<ConfirmAccountToken,Integer> {
    ConfirmAccountToken findFirstByToken(String token);

}
