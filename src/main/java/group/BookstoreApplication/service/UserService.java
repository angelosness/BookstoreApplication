package group.BookstoreApplication.service;

import group.BookstoreApplication.formsdata.SearchDTO;
import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.BookCategory;
import group.BookstoreApplication.model.User;

import java.util.List;

public interface UserService {
    public boolean userRegister(User theUser);

    public void addOffer(String username, Book theBook);

    public List<BookCategory> retrieveCategories();

    public List<Book> searchBooks(SearchDTO searchData);
}
