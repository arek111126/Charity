package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.config.UserInSessionService;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;

import java.util.Collection;


@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    UserInSessionService userInSessionService;


    @RequestMapping("/")
    public String homeAction(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRetypePassword(encodedPassword);
        user.setEnabled(1);
        user.getRoles().add(roleService.findFirstByRoleName("ROLE_USER"));
        userService.save(user);
        return "redirect:/";
    }


    @GetMapping("/redirect")
    public String getForm(Model model) {

        Collection<? extends GrantedAuthority> authoriesFromSession = userInSessionService.getAuthoriesFromSession();
        String authories = authoriesFromSession.toString();
        if (authories.contains("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("donation", new Donation());

        return "redirect:/app/donation/add";

    }

}
