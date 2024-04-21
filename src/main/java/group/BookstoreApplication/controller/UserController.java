package group.BookstoreApplication.controller;

import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;
import group.BookstoreApplication.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model theModel) {
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "register";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        if (userService.userRegister(theUser)) return "redirect:/login";
        else return "redirect:/register?error";
    }

    // same logic as login
    @RequestMapping("/offer")
    public String offerBook(Model theModel) {
        Book theBook = new Book();

        // max 3 authors
        for (int i=0; i<3; i++) {
            BookAuthor author = new BookAuthor();
            author.getBooks().add(theBook);
            theBook.getBookAuthors().add(author);
        }

        theModel.addAttribute("book", theBook);

        List<BookCategory> categoryList = userService.retrieveCategories();
        theModel.addAttribute("categoryList", categoryList);

        return "offer";
    }

    @RequestMapping("/request")
    public String requestBook(Model theModel) {

        return "request";
    }

    @RequestMapping("/profile")
    public String profile(Model theModel) {

        return "profile";
    }

    @RequestMapping("/offer/complete")
    public String completeOffer(@ModelAttribute("book") Book theBook) {
        userService.addOffer(SecurityContextHolder.getContext().getAuthentication().getName(),theBook);
        return "redirect:/";
    }
}
