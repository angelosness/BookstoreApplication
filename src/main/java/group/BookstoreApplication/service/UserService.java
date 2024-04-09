package group.BookstoreApplication.service;

import group.BookstoreApplication.model.User;

public interface UserService {
    public boolean userLogin(User theUser);

    public void userRegister(User theUser);
}
