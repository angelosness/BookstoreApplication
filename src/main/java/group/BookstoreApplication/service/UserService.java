package group.BookstoreApplication.service;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.formsdata.ProfileDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.List;

public interface UserService {
    public boolean userRegister(User theUser);

    public boolean addOffer(String username, Book theBook);

    public List<BookCategory> retrieveCategories();

    public List<Book> searchBooks(SearchDTO searchData);

    public List<Book> retrieveOfferList(String username);

    public void removeBook(int id);

    public User retrieveProfile(String username);

    public void updateUser(ProfileDTO profileData);

    public void requestBook(String username, int id);

    public void offerBookToUser(int userId, int bookId);

    public List<Book> retrieveRequestList(String username);

    public List<Book> showRecommendations(String strategy, User user);
}
