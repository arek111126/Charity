package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.config.UserInSessionService;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validateGroup.ValidationUserInfoGroup;
import pl.coderslab.charity.validateGroup.ValidationUserPasswordGroup;

import java.util.List;

@Controller
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserInSessionService userInSessionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/changeBasicData")
    public String getChangeDataForm(Model model) {
        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        user.setHiddenId(user.getId());
        model.addAttribute("user", user);
        return "edit-user";
    }


    @PostMapping("/changeBasicData")
    public String changeUserData(@Validated({ValidationUserInfoGroup.class}) User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "edit-user";
        }
        userService.editUserInfo(user);
        model.addAttribute("donation", new Donation());

        return "form";

    }

    @GetMapping("/changePassword")
    public String changeUserPasswordForm(Model model){
        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        user.setHiddenId(user.getId());
        model.addAttribute("user",user);
        return "edit-user-password";
    }

    @PostMapping("/changePassword")
    public String changeUserPassword(@Validated({ValidationUserPasswordGroup.class}) User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            return "edit-user-password";
        }
        userService.editPassword(user);
        return "redirect:/app/user/changeBasicData";

    }

}
