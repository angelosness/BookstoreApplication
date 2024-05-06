package group.BookstoreApplication.service;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.dao.BookAuthorDAO;
import group.BookstoreApplication.dao.BookCategoryDAO;
import group.BookstoreApplication.dao.BookDAO;
import group.BookstoreApplication.dao.UserDAO;
import group.BookstoreApplication.formsdata.ProfileDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;
import group.BookstoreApplication.service.recommendations.RecommendationsFactory;
import group.BookstoreApplication.service.recommendations.RecommendationsStrategy;
import group.BookstoreApplication.service.search.SearchFactory;
import group.BookstoreApplication.service.search.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private BookDAO bookRepository;

    @Autowired
    private BookCategoryDAO categoryRepository;

    @Autowired
    private BookAuthorDAO authorRepository;

    @Override
    public boolean userRegister(User theUser) {
        // If user entered an already used username.
        if (userRepository.findByUsername(theUser.getUsername()) != null)
            return false;

        // encode password
        String encodedPassword = bCryptPasswordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);

        userRepository.save(theUser);
        return true;
    }

    @Override
    public void addOffer(String username, Book theBook) {
        List<BookAuthor> authorsList = theBook.getBookAuthors();

        List<BookAuthor> finalList = new ArrayList<BookAuthor>();
        for (BookAuthor author : authorsList) {
            // check if field is empty
            String authorName = author.getName();
            if (!authorName.isEmpty()) {
                // check if author already exists in db
                // retrieve author's book list and update it
                BookAuthor authorDB = authorRepository.findByName(authorName);

                if (authorDB != null) {
                    author = authorDB;
                    author.getBooks().add(theBook);
                }

                finalList.add(author);
            }
        }
        theBook.setBookAuthors(finalList);

        // retrieve category from db
        // update category book list
        BookCategory category = categoryRepository.findByName(theBook.getBookCategory().getName());
        category.getBooks().add(theBook);
        theBook.setBookCategory(category);

        // add book to personal list
        User theUser = userRepository.findByUsername(username);

        theBook.setOfferingUser(theUser);
        theUser.getBookOffers().add(theBook);

        // update user
        userRepository.save(theUser);
    }

    @Override
    public List<Book> searchBooks(SearchDTO searchData) {
        SearchStrategy searchType = SearchFactory.create(searchData.getSearchStrategy());

        //Return results
        return searchType.search(searchData, bookRepository);

    }

    @Override
    public List<Book> showRecommendations(String strategy, User user){
        RecommendationsStrategy recommendationType = RecommendationsFactory.create(strategy);

        //Return results
        return recommendationType.getRecommendations(user, bookRepository);
    }

    // for loading all preloaded book categories into the offer form
    @Override
    public List<BookCategory> retrieveCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Book> retrieveOfferList(String username) {
        return userRepository.findByUsername(username).getBookOffers();
    }

    // for removing a book from personal offer list
    @Override
    public void removeBook(int id) {
        bookRepository.deleteById(id);
    }

    // for showing account information
    @Override
    public User retrieveProfile(String username) {
        return userRepository.findByUsername(username);
    }

    // for user profile update
    @Override
    public void updateUser(ProfileDTO profileData) {
        // update user's favorite categories
        List<String> categoryList = profileData.getCategories();
        for (String s : categoryList) {
            profileData.getUser().getFavoriteCategories().add(categoryRepository.findByName(s));
        }

        userRepository.save(profileData.getUser());
    }

    @Override
    public void requestBook(String username, int id) {
        User user = userRepository.findByUsername(username);
        Book book = bookRepository.findById(id);

        book.getRequestingUsers().add(user);

        bookRepository.save(book);
    }

    @Override
    public void offerBookToUser(int userId, int bookId) {
        Book book = bookRepository.findById(bookId);
        book.setAcceptedUser(userRepository.findById(userId));

        book.setStatus("UNAVAILABLE");

        bookRepository.save(book);
    }

    @Override
    public List<Book> retrieveRequestList(String username) {
        return userRepository.findByUsername(username).getRequestedBooks();
    }

    // for UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND %s", username));
        }
        return user;
    }
}
