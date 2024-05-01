package group.BookstoreApplication.dao;

import group.BookstoreApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
    public Book findById(int id);

    public List<Book> findByTitle(String title);

    public List<Book> findByTitleContaining(String title);
}
