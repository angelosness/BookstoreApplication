package group.BookstoreApplication.service;

import group.BookstoreApplication.dao.UserDAO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean userLogin(User theUser) {
        User result = userRepository.findByUsernameAndPassword(theUser.getUsername(), theUser.getPassword());

        return result != null;
    }

    @Override
    public boolean userRegister(User theUser) {
        //If user entered an already used username.
        if (userRepository.findByUsername(theUser.getUsername()) != null)
            return false;

        userRepository.save(theUser);
        return true;
    }

    @Override
    public void addOffer(User theUser, Book theBook) {
        // add book to personal list
        List<Book> bookOffers = theUser.getBookOffers();
        bookOffers.add(theBook);

        // add book to author list if author exists

        // add book to category list

        // update user
        userRepository.save(theUser);
    }
}
