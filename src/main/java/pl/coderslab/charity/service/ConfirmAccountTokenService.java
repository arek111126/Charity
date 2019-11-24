package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.token.ConfirmAccountToken;
import pl.coderslab.charity.repository.ConfirmAccountTokenRepository;

@Component
public class ConfirmAccountTokenService {

    @Autowired
    ConfirmAccountTokenRepository confirmAccountTokenRepository;

    public ConfirmAccountToken save(ConfirmAccountToken confirmAccountToken) {
        return confirmAccountTokenRepository.save(confirmAccountToken);
    }

    public ConfirmAccountToken findByToken(String token) {
        return confirmAccountTokenRepository.findFirstByToken(token);
    }

    public void delete(ConfirmAccountToken confirmAccountToken) {
        confirmAccountTokenRepository.delete(confirmAccountToken);
    }
}
