package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class InstitutionAdminController {

    @Autowired
    InstitutionService institutionService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAll();
    }

    @GetMapping("/institutions")
    public String getInstitutions(Model model) {
        return "adminTemplates/all-institiution";
    }


    @PostMapping("/institution/{id}/delete")
    public String deleteInstitution(@PathVariable int id, Model model) {
        institutionService.delete(institutionService.findById(id));
        model.addAttribute("institutions", institutionService.findAll());
        return "adminTemplates/all-institiution";
    }

    @GetMapping("/institution/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "adminTemplates/new-institution";
    }

    @PostMapping("/institution/add")
    public String addNewInstitution(@ModelAttribute("institution") Institution institution, Model model) {
        institutionService.save(institution);

        model.addAttribute("institution", new Institution());
        return "adminTemplates/new-institution";
    }

    @GetMapping("/institution/{id}/edit")
    public String editInstitution(@PathVariable int id, Model model) {
        Institution institution = institutionService.findById(id);
        institution.setHiddenId(institution.getId());
        model.addAttribute("institution", institution);
        return "/adminTemplates/edit-institution";
    }

    @PostMapping("/institution/edit")
    public String editInstitution(@ModelAttribute("institution") Institution institution) {
        Institution institutionFromDatabase = institutionService.findById(institution.getHiddenId());
        institutionFromDatabase.setCategories(institution.getCategories());
        institutionFromDatabase.setDescription(institution.getDescription());
        institutionFromDatabase.setName(institution.getName());
        institutionService.save(institutionFromDatabase);
        return "redirect:/admin/institutions";
    }
}
