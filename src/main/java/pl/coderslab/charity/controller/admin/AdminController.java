package pl.coderslab.charity.controller.admin;
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
    public String ShowAllAdmins(Model model) {

        model.addAttribute("users", userService.findUsersByRolesContains(roleService.findFirstByRoleName("ROLE_ADMIN")));

        return "adminTemplates/all-admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/all";
    }

    @PostMapping("/{id}/block")
    public String blockUser(@PathVariable int id) {
        userService.block(userService.findById(id));
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
        //find current user and edit. For update databse necessery is id so i muss find full user entity from database ( @Setter for id from security reason is disable )
        userService.editUserInfo(user);

        return "redirect:/admin/all";
    }

    @GetMapping("/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("user", new User());

        return "adminTemplates/add-admin";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("user") User user) {

        user.getRoles().add(roleService.findFirstByRoleName("ROLE_USER"));
        user.getRoles().add(roleService.findFirstByRoleName("ROLE_ADMIN"));
        userService.save(user);

        return "redirect:/admin/add";
    }
    @GetMapping("/{id}/changePassword")
    public String editPasswordForm(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setHiddenId(id);
        model.addAttribute("user", user);

        return "adminTemplates/edit-admin-password";
    }
    @PostMapping("/changePassword")
    public String editPasswordForm(@ModelAttribute("user") User user){
        userService.editPassword(user);
        return "redirect:/admin/"+user.getHiddenId()+"/edit";
    }


}
