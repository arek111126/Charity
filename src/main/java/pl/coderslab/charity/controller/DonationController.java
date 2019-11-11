package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.model.StatisticData;
import pl.coderslab.charity.service.DonationService;

@Controller
@RequestMapping("/app/donation")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping("/staticData")
    @ResponseBody
    public StatisticData getStatisticData(){
        return donationService.countStatisticData();
    }
}
