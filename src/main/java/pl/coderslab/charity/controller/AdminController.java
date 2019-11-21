package pl.coderslab.charity.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    InstitutionService institutionService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RoleService roleService;

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAll();
    }

    @GetMapping("/dashboard")
    public String adminPage() {
        return "adminTemplates/index";
    }


    @GetMapping("/admin/all")
    public String ShowAllAdmins(Model model){

        model.addAttribute("users",userService.findUsersByRolesContains(roleService.findFirstByRoleName("ROLE_ADMIN")));

        return "adminTemplates/all-admin";
    }
    @PostMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable int id, Model model) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/admin/all";
    }

    @PostMapping("/admin/{id}/block")
    public String blockUser(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setRetypePassword(user.getPassword());
        if (user.getEnabled() == 1) {
            user.setEnabled(0);
        } else {
            user.setEnabled(1);
        }
        userService.save(user);
        return "redirect:/admin/admin/all";
    }







}
