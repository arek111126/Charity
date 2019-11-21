package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.Role;
import pl.coderslab.charity.entity.authentication.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
            User findFirstByEmail(String email);
            User findByDonations(Donation donation);
            User findById(int id);
            List<User> findUsersByRolesContains(Role role);

}
