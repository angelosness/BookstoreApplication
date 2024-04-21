package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorDAO extends JpaRepository<BookAuthor, Integer> {
    public BookAuthor findByName(String name);
}
