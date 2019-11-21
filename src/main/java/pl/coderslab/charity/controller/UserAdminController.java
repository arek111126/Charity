package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserAdminController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;


    @GetMapping("/user/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.findUsersByRolesNotContains(roleService.findFirstByRoleName("ROLE_ADMIN")));

        return "adminTemplates/all-user";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/user/all";
    }

    @PostMapping("/user/{id}/block")
    public String blockUser(@PathVariable int id) {
        User user = userService.findById(id);
        user.setRetypePassword(user.getPassword());
        if (user.getEnabled() == 1) {
            user.setEnabled(0);
        } else {
            user.setEnabled(1);
        }
        userService.save(user);
        return "redirect:/admin/user/all";
    }

    @GetMapping("/user/{id}/edit")
    public String getEditedPage(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setHiddenId(user.getId());
        model.addAttribute("user", user);
        return "adminTemplates/edit-user";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute("user") User user) {
        User userFromDatabase = userService.findById(user.getHiddenId());
        if (user.isChangePassword()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            userFromDatabase.setPassword(encodedPassword);
            userFromDatabase.setRetypePassword(encodedPassword);
        } else {
            userFromDatabase.setRetypePassword(userFromDatabase.getPassword());
        }
        userFromDatabase.setFirstName(user.getFirstName());
        userFromDatabase.setSecondName(user.getSecondName());
        userFromDatabase.setEmail(user.getEmail());
        userFromDatabase.setEnabled(1);
        userService.save(userFromDatabase);

        return "redirect:/admin/user/all";
    }
}
