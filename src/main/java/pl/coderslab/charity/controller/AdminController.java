package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    InstitutionService institutionService;

    @ModelAttribute("institutions")
    public List<Institution> getAllCategories() {
        return institutionService.findAll();
    }

    @GetMapping("/dashboard")
    public String adminPage() {
        return "adminTemplates/index";
    }

    @GetMapping("/institutions")
    public String getInstitutions(Model model) {
        return "adminTemplates/tables";
    }

    @PostMapping("/institution/{id}/delete")
    public String deleteInstitution(@PathVariable int id, Model model) {
        institutionService.delete(institutionService.findById(id));
        model.addAttribute("institutions", institutionService.findAll());
        return "adminTemplates/tables";
    }
}
