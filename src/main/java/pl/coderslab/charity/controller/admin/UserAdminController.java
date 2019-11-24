package pl.coderslab.charity.controller.admin;


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

        userService.block(userService.findById(id));
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

        userService.editUserInfo(user);
        return "redirect:/admin/user/all";
    }

    @GetMapping("/user/{id}/changePassword")
    public String editPasswordForm(@PathVariable int id, Model model) {
        User user = userService.findById(id);
        user.setHiddenId(id);
        model.addAttribute("user", user);

        return "adminTemplates/edit-user-password";
    }

    @PostMapping("/user/changePassword")
    public String editPasswordForm(@ModelAttribute("user") User user) {
        userService.editPassword(user);
        return "redirect:/admin/user/" + user.getHiddenId() + "/edit";
    }


}
