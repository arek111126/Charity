package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.model.StatisticData;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    Donation findFirstById(int id);

    @Query("SELECT new pl.coderslab.charity.model.StatisticData(" +
            "sum(d.quantity)," +
            "count(distinct d.institution)) FROM Donation d")
    StatisticData countStatisticData();
}
