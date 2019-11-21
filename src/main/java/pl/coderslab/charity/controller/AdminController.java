package pl.coderslab.charity.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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


    @GetMapping("/all")
    public String ShowAllAdmins(Model model){

        model.addAttribute("users",userService.findUsersByRolesContains(roleService.findFirstByRoleName("ROLE_ADMIN")));

        return "adminTemplates/all-admin";
    }
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id, Model model) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/all";
    }

    @PostMapping("/{id}/block")
    public String blockUser(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setRetypePassword(user.getPassword());
        if (user.getEnabled() == 1) {
            user.setEnabled(0);
        } else {
            user.setEnabled(1);
        }
        userService.save(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/{id}/edit")
    public String getEditedPage(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setHiddenId(user.getId());
        model.addAttribute("user", user);
        return "adminTemplates/edit-admin";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        User userFromDatabase = userService.findById(user.getHiddenId());
        if (user.isChangePassword()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            userFromDatabase.setPassword(encodedPassword);
            userFromDatabase.setRetypePassword(encodedPassword);
        }else{
            userFromDatabase.setRetypePassword(userFromDatabase.getPassword());
        }
        userFromDatabase.setFirstName(user.getFirstName());
        userFromDatabase.setSecondName(user.getSecondName());
        userFromDatabase.setEmail(user.getEmail());
        userFromDatabase.setEnabled(1);
        userService.save(userFromDatabase);

        return "redirect:/admin/all";
    }







}
