package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.config.UserInSessionService;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.Role;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInSessionService userInSessionService;

    @Secured("ROLE_ADMIN")
    public User block(User user) {

        user.setRetypePassword(user.getPassword());
        if (user.getEnabled() == 1) {
            user.setEnabled(0);
        } else {
            user.setEnabled(1);
        }
        return userRepository.save(user);

    }

    public User editPassword(User user){
        User userFromDatabase = userRepository.findById(user.getHiddenId());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userFromDatabase.setPassword(encodedPassword);
        return   userRepository.save(userFromDatabase);

    }


    public User editUserInfo(User user) {
        User userFromDatabase = userRepository.findById(user.getHiddenId());
        userFromDatabase.setFirstName(user.getFirstName());
        userFromDatabase.setSecondName(user.getSecondName());
        userFromDatabase.setEmail(user.getEmail());
        return userRepository.save(userFromDatabase);
    }

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRetypePassword(encodedPassword);
        user.setEnabled(1);
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

    @Secured("ROLE_ADMIN")
    public void delete(User user) {
        if (!user.getEmail().equals(userInSessionService.getUserFromSessionByLogin())) {
            userRepository.delete(user);
        }


    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findUsersByRolesContains(Role role) {
        return userRepository.findUsersByRolesContains(role);
    }

    public List<User> findUsersByRolesNotContains(Role role) {
        return userRepository.findUsersByRolesNotContains(role);
    }


}
