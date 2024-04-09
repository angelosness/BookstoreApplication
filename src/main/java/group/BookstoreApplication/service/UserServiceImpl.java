package group.BookstoreApplication.service;

import group.BookstoreApplication.dao.UserDAO;
import group.BookstoreApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //Checking if credentials are valid:
        String username = theUser.getUsername();
        String password = theUser.getPassword();

        if(username.isEmpty() || password.isEmpty())  //If user entered a blank username.
            return false;

        if(password.length() < 8)     //If user entered a password with less than 8 characters or didn't enter a password at all.
            return false;

        if (userRepository.findByUsername(username) != null)   //If the user entered an already used username
            return false;

        userRepository.save(theUser);
        return true;

    }
}
