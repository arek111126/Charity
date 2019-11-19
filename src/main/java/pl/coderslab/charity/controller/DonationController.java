package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.config.UserInSessionService;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/app/donation")
public class DonationController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    InstitutionService institutionService;

    @Autowired
    DonationService donationService;

    @Autowired
    UserInSessionService userInSessionService;

    @Autowired
    UserService userService;


    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }


    @GetMapping("/add")
    public String getForm(Model model) {

        model.addAttribute("donation", new Donation());

        return "form";

    }

    @PostMapping("/add")
    public String addDonation(@Valid Donation donation, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "form";
        }
        donation.setPickUpDateTime(LocalDateTime.of(donation.getPickUpDate(), donation.getPickUpTime()));

        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        donation.setUser(user);
        donationService.save(donation);
        return "form-confirmation";

    }

    @GetMapping("/userDonation")
    public String userDonation(Model model) {

        List<Donation> donations = donationService.findAllbyUser(userService.findByEmail(userInSessionService.getUserFromSessionByLogin()));
        model.addAttribute("donations", donations);
        return "user-donation";
    }

    @GetMapping("/detail")
    public String donation(@RequestParam int id, Model model) {
        String userInSessionLogin = userInSessionService.getUserFromSessionByLogin();
        Donation donation = donationService.findById(id);
        User user = userService.findByDonation(donation);
        if (user != null && user.getEmail().equals(userInSessionLogin)) {
            model.addAttribute("donation", donation);

            return "donation-detail";
        }
        return "donation-detail";

    }
}
