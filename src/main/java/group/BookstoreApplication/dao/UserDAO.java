package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    public User findById(int id);

    public User findByUsername(String username);
}
