package group.BookstoreApplication.service;

<<<<<<< Updated upstream
import group.BookstoreApplication.formsdata.SearchDTO;
=======
import group.BookstoreApplication.formsdata.ProfileDTO;
>>>>>>> Stashed changes
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookAuthor;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.List;

public interface UserService {
    public boolean userRegister(User theUser);

    public void addOffer(String username, Book theBook);

    public List<BookCategory> retrieveCategories();

<<<<<<< Updated upstream
    public List<Book> searchBooks(SearchDTO searchData);
=======
    public List<Book> retrievePersonalList(String username);

    public void removeBook(int id);

    public User retrieveProfile(String username);

    public void updateUser(ProfileDTO profileData);
>>>>>>> Stashed changes
}
