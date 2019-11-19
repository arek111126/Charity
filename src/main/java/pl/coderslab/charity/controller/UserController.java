package pl.coderslab.charity.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app")
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

    @GetMapping("/changeUserData")
    public String getChangeDataForm(Model model){
        User user = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        model.addAttribute("user",user);

        return "change-user-data";
    }


    @PostMapping("/changeUserData")
    public String changeUserData(@Valid User user, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "change-user-data";
        }
        User userInSession = userService.findByEmail(userInSessionService.getUserFromSessionByLogin());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userInSession.setEmail(user.getEmail());
        userInSession.setPassword(encodedPassword);
        userInSession.setRetypePassword(encodedPassword);
        userInSession.setFirstName(user.getFirstName());
        userInSession.setSecondName(user.getSecondName());
        userService.save(userInSession);
        model.addAttribute("donation", new Donation());

        return "form";

    }
}
