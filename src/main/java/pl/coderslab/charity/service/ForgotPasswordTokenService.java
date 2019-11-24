package pl.coderslab.charity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.entity.token.ForgotPasswordToken;
import pl.coderslab.charity.exception.InvalidPasswrodTokenException;
import pl.coderslab.charity.repository.ForgotPasswordTokenRepository;

import javax.swing.*;

@Component
public class ForgotPasswordTokenService {

    @Autowired
    ForgotPasswordTokenRepository forgotPasswordTokenRepository;

    public ForgotPasswordToken save(ForgotPasswordToken forgotPasswordToken) {
        return forgotPasswordTokenRepository.save(forgotPasswordToken);
    }

    public void delete(ForgotPasswordToken forgotPasswordToken) {
        forgotPasswordTokenRepository.delete(forgotPasswordToken);
    }

    public ForgotPasswordToken findByToken(String token) throws InvalidPasswrodTokenException {
        ForgotPasswordToken foundedToken = forgotPasswordTokenRepository.findFirstByToken(token);
        if (foundedToken == null) {
            throw new InvalidPasswrodTokenException("Nie można zmienić hasła. Nieporpawny link");
        }
        return foundedToken;
    }

    public ForgotPasswordToken findByEmail(String email) throws InvalidPasswrodTokenException {
        ForgotPasswordToken forgotPasswordToken = forgotPasswordTokenRepository.findFirstByUserEmail(email);
        if (forgotPasswordToken == null) {
            throw new InvalidPasswrodTokenException("Nie można zmienić hasła nieporpawny token");
        }
        return forgotPasswordToken;
    }

}
