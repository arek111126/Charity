package pl.coderslab.charity.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.model.StatisticData;
import pl.coderslab.charity.service.DonationService;

@RestController
@RequestMapping("/app/donation")
public class DonationRestController {

    @Autowired
    DonationService donationService;

    @GetMapping("/statistic")
    public StatisticData getStatisticData(){
        return donationService.countStatisticData();
    }

}

