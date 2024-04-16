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
        //If user entered an already used username.
        if (userRepository.findByUsername(theUser.getUsername()) != null)
            return false;

        userRepository.save(theUser);
        return true;
    }
}
