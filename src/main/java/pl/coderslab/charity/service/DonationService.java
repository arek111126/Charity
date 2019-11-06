package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonatioRepository;

@Component
public class DonationService {

    @Autowired
    DonatioRepository donatioRepository;

    public Donation save(Donation donation) {
        return donatioRepository.save(donation);

    }

    public void delete(Donation donation) {
        donatioRepository.delete(donation);
    }

    public Donation findById(int id) {
        return donatioRepository.findFirstById(id);
    }
}
