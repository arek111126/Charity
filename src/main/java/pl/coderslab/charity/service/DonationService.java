package pl.coderslab.charity.service;

import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.model.StatisticData;
import pl.coderslab.charity.repository.DonatioRepository;

import java.util.List;

@Service
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

    public StatisticData countStatisticData(){
        return donatioRepository.countStatisticData();
    }
}
