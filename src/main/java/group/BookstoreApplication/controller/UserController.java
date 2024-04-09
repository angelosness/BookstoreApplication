package group.BookstoreApplication.controller;

import group.BookstoreApplication.model.User;
import group.BookstoreApplication.service.UserService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(Model theModel) {
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "login";
    }

    @RequestMapping("/auth")
    public String authorizeUser(@ModelAttribute("user") User theUser) {
        if (userService.userLogin(theUser)) return "redirect:/homepage";
        else return "redirect:/login";
    }
}
