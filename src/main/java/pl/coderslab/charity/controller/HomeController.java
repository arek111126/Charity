package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.config.UserInSessionService;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.authentication.ConfirmAccountToken;
import pl.coderslab.charity.entity.authentication.User;
import pl.coderslab.charity.service.ConfirmAccountTokenService;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validateGroup.EmailValidationGroup;
import pl.coderslab.charity.validateGroup.ValidationUserPasswordGroup;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;


@Controller
public class HomeController {


    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    UserInSessionService userInSessionService;

    @Autowired
    ConfirmAccountTokenService confirmAccountTokenService;


    @RequestMapping("/")
    public String homeAction(Model model) {
        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        model.addAttribute("user", user != null ? user : new User());
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
    public String registerNewUser(@Validated({ValidationUserPasswordGroup.class, EmailValidationGroup.class}) User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createNewUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        model.addAttribute("successAlert", "Link aktywacyjny został wysłany na podany adres email");
        return "index";
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

    @GetMapping("/confirmAccount")
    public String confirmAccount(@RequestParam String token, Model model) {
        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        model.addAttribute("user", user != null ? user : new User());

        ConfirmAccountToken confirmAccountToken = confirmAccountTokenService.findByToken(token);
        if (confirmAccountToken == null) {
            model.addAttribute("dengerAlert", "konto nie istnieje bądź zostało juz aktywowane");
            return "index";
        }
        confirmAccountToken.getUser().setEnabled(1);
        confirmAccountTokenService.save(confirmAccountToken);
        confirmAccountTokenService.delete(confirmAccountToken);
        model.addAttribute("successAlert", "Konto zostało aktywowane");
        return "index";

    }


}
