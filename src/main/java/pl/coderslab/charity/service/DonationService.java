package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.model.StatisticData;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donatioRepository;

    public Donation save(Donation donation) {
        return donatioRepository.save(donation);

    }

    public void delete(Donation donation) {
        donatioRepository.delete(donation);
    }

    public Donation findById(int id) {
        return donatioRepository.findFirstById(id);
    }

    public StatisticData countStatisticData() {
        return donatioRepository.countStatisticData();
    }


    public List<Donation> findAllbyUser(User user) {
        return donatioRepository.findAllByUser(user);
    }


    public Donation changeStatus(int id){
        Donation donation = donatioRepository.findFirstById(id);
        if (donation.getReceived()==0){

            donation.setRealPickUpDateTime(LocalDateTime.now());
            donation.setReceived(1);
        }else {
            donation.setRealPickUpDateTime(null);
            donation.setReceived(0);
        }
       return donatioRepository.save(donation);

    }
}
