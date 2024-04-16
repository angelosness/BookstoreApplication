package group.BookstoreApplication.service;

import group.BookstoreApplication.model.Book;
import group.BookstoreApplication.model.User;

public interface UserService {
    public boolean userLogin(User theUser);

    public boolean userRegister(User theUser);

    public void addOffer(User theUser, Book theBook);
}
