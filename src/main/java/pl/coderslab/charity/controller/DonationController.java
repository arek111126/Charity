package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

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


    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("allInstitution")
    public List<Institution> getAllInstitution() {
        return institutionService.findAll();
    }

    @GetMapping("/add")
    public String getForm(Model model) {
        model.addAttribute("donation", new Donation());

        return "form";

    }

    @PostMapping("/add")
    public String addDonation(@ModelAttribute("donation") Donation donation, Model model) {
        donation.setPickUpDateTime(LocalDateTime.of(donation.getPickUpDate(), donation.getPickUpTime()));
        donationService.save(donation);
        return "form";

    }
}
