package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.authentication.User;

public interface UserRepository extends JpaRepository<User,Integer> {
            User findFirstByEmail(String email);
}
