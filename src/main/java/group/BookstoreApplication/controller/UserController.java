package group.BookstoreApplication.controller;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.formsdata.ProfileDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/")
    public String homepage(@RequestParam(value = "strategy", defaultValue = "AuthorAndCategories") String strategy, Model theModel) {
        SearchDTO searchData = new SearchDTO();
        theModel.addAttribute("searchData", searchData);

        User theUser = userService.retrieveProfile(SecurityContextHolder.getContext().getAuthentication().getName());
        theModel.addAttribute("user", theUser);


        //Showing Recommendations on Homepage:
        //Recommendations based on user's favorite categories:
        List<Book> recommendationResult = userService.showRecommendations(strategy, theUser);

        //Strategy gets passed to theModel, in order to display the correct tile for the results
        theModel.addAttribute("strategy", strategy);
        theModel.addAttribute("books", recommendationResult);

        return "homepage";
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
        for (int i = 0; i < 3; i++) {
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
    public String sendRequest(@RequestParam("bookId") int theId) {
        userService.requestBook(SecurityContextHolder.getContext().getAuthentication().getName(), theId);

        return "redirect:/?success";
    }

    @RequestMapping("/profile")
    public String showProfile(Model theModel) {
        User theUser = userService.retrieveProfile(SecurityContextHolder.getContext().getAuthentication().getName());

        ProfileDTO profileData = new ProfileDTO(theUser);
        theModel.addAttribute("formdata", profileData);

        List<BookCategory> categoryList = userService.retrieveCategories();
        theModel.addAttribute("categoryList", categoryList);

        return "user/profile";
    }

    @RequestMapping("/offer/complete")
    public String completeOffer(@ModelAttribute("book") Book theBook) {
        if (userService.addOffer(SecurityContextHolder.getContext().getAuthentication().getName(), theBook)) return "redirect:/offer?success";
        else return "redirect:/offer?error";
    }

    @RequestMapping("/search")
    public String search(@ModelAttribute("formdata") SearchDTO searchData, Model theModel) {
        List<Book> searchResult = userService.searchBooks(searchData);

        // retrieve current user information to check if he is able to request
        User thisUser = userService.retrieveProfile(SecurityContextHolder.getContext().getAuthentication().getName());
        theModel.addAttribute("user", thisUser);

        theModel.addAttribute("books", searchResult);

        return "search";
    }

    @RequestMapping("/list")
    public String showOfferList(Model theModel) {
        List<Book> personalList = userService.retrieveOfferList(SecurityContextHolder.getContext().getAuthentication().getName());

        theModel.addAttribute("offerList", personalList);

        return "user/list";
    }

    @RequestMapping("/list/delete")
    public String deleteOffer(@RequestParam("bookId") int theId) {
        userService.removeBook(theId);

        return "redirect:/list";
    }

    @RequestMapping("/profile/save")
    public String updateProfile(@ModelAttribute("formdata") ProfileDTO profileData) {
        userService.updateUser(profileData);

        return "redirect:/profile?success";
    }

    @RequestMapping("/list/give")
    public String giveBook(@RequestParam("userId") int userId, @RequestParam("bookId") int bookId) {
        userService.offerBookToUser(userId, bookId);

        return "redirect:/list";
    }

    @RequestMapping("/reqlist")
    public String showRequestList(Model theModel) {
        List<Book> requestList = userService.retrieveRequestList(SecurityContextHolder.getContext().getAuthentication().getName());

        theModel.addAttribute("requestList", requestList);

        User thisUser = userService.retrieveProfile(SecurityContextHolder.getContext().getAuthentication().getName());
        theModel.addAttribute("user", thisUser);

        return "user/requests";
    }
}