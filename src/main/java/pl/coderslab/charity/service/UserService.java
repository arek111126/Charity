package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.Role;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public User findByDonation(Donation donation) {
        return userRepository.findByDonations(donation);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findById(int id) {
       return userRepository.findById(id);
    }

    public List<User> findUsersByRolesContains(Role role){
       return userRepository.findUsersByRolesContains(role);
    }

}
