package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

   public User  save(User user){
        return userRepository.save(user);
    }
}
